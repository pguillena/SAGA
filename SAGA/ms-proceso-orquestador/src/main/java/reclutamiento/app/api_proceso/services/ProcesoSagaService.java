package reclutamiento.app.api_proceso.services;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reclutamiento.app.api_proceso.domain.SagaStateMachine;
import reclutamiento.app.api_proceso.domain.SagaStatus;
import reclutamiento.app.api_proceso.domain.SagaType;
import reclutamiento.app.api_proceso.entities.SagaEntity;
import reclutamiento.app.api_proceso.factory.SagaCommandFactory;
import reclutamiento.app.api_proceso.mappers.SagaPayloadMapper;
import reclutamiento.app.api_proceso.publisher.SagaCommandPublisher;
import reclutamiento.app.api_proceso.repositories.SagaRepository;
import reclutamiento.app.api_proceso.resources.dtos.events.*;
import reclutamiento.app.api_proceso.resources.dtos.request.ProcesoRequest;
import reclutamiento.app.api_proceso.resources.dtos.saga.CreateProcesoSagaPayload;
import reclutamiento.app.api_proceso.resources.dtos.saga.SagaStatusResponse;

import java.util.UUID;

import static reclutamiento.app.api_proceso.messages.ProductSagaMessages.SAGA_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class ProcesoSagaService {

    private final SagaRepository sagaRepository;
    private final SagaCommandPublisher publisher;
    private final SagaPayloadMapper payloadMapper;
    private final SagaCommandFactory commandFactory;
    private final SagaStateMachine stateMachine;

    public UUID startCreateProcesoSaga(ProcesoRequest request) {

        UUID sagaId = UUID.randomUUID();

        CreateProcesoSagaPayload payload =
                CreateProcesoSagaPayload.builder()
                        .listaProcesoComite(request.listaProcesoComite())
                        .build();

        SagaEntity saga = SagaEntity.builder()
                .id(sagaId)
                .type(SagaType.CREATE_PROCESO_WITH_COMITE)
                .status(SagaStatus.STARTED)
                .payload(payloadMapper.toJson(payload))
                .build();

        sagaRepository.save(saga);

        publisher.sendCreateProcesoCommand(commandFactory.buildCreateProcesoCommand(request, sagaId));

        return sagaId;
    }

    public void handleProcesoCreated(ProcesoCreatedEvent event) {

        SagaEntity saga = sagaRepository.findById(event.getSagaId())
                .orElseThrow(() -> new ProcesoSagaException(SAGA_NOT_FOUND));

        stateMachine.validateTransition(
                saga.getType(),
                saga.getStatus(),
                SagaStatus.PROCESO_CREATED
        );

        saga.setStatus(SagaStatus.PROCESO_CREATED);

        sagaRepository.save(saga);

        CreateProcesoSagaPayload payload = payloadMapper.fromJson(saga.getPayload());

        publisher.sendCreateProcesoComiteCommand(
                commandFactory.buildCreateProcesoComiteCommand(
                        event.getSagaId(),
                        event.getProcesoId(),
                        payload
                )
        );



    }

    public void handleProcesoComiteCreated(ProcesoComiteCreatedEvent event) {

        SagaEntity saga = sagaRepository.findById(event.getSagaId())
                .orElseThrow(() -> new ProcesoSagaException(SAGA_NOT_FOUND));

        stateMachine.validateTransition(
                saga.getType(),
                saga.getStatus(),
                SagaStatus.COMITE_CREATED
        );

        saga.setStatus(SagaStatus.COMITE_CREATED);

        sagaRepository.save(saga);

        /*
        stateMachine.validateTransition(
                saga.getType(),
                saga.getStatus(),
                SagaStatus.COMPLETED
        );*/

        //saga.setStatus(SagaStatus.COMPLETED);

        //sagaRepository.save(saga);

        CreateProcesoSagaPayload payload = payloadMapper.fromJson(saga.getPayload());

        publisher.sendNotificationProcesoComiteCommand(
                commandFactory.buildNotificationProcesoComiteCommand(
                        event.getSagaId(),
                        event.getProcesoId(),
                        payload
                )
        );
    }

    public void handleProcesoComiteCreationFailed(ProcesoComiteCreationFailedEvent event) {

        SagaEntity saga = sagaRepository.findById(event.getSagaId())
                .orElseThrow(() -> new ProcesoSagaException(SAGA_NOT_FOUND));

        stateMachine.validateTransition(
                saga.getType(),
                saga.getStatus(),
                SagaStatus.COMPENSATING
        );

        saga.setStatus(SagaStatus.COMPENSATING);

        sagaRepository.save(saga);

        publisher.sendDeleteProcesoCommand(
                event.getProcesoId(),
                event.getSagaId()
        );
    }

    public void handleProcesoDeleted(ProcesoDeletedEvent event) {

        SagaEntity saga = sagaRepository.findById(event.getSagaId())
                .orElseThrow(() -> new ProcesoSagaException(SAGA_NOT_FOUND));

        stateMachine.validateTransition(
                saga.getType(),
                saga.getStatus(),
                SagaStatus.COMPENSATED
        );

        saga.setStatus(SagaStatus.COMPENSATED);
        sagaRepository.save(saga);
    }

    public SagaStatusResponse getSagaStatus(UUID sagaId) {

        SagaEntity saga = sagaRepository.findById(sagaId)
                .orElseThrow(() -> new ProcesoSagaException(SAGA_NOT_FOUND));

        return SagaStatusResponse.builder()
                .sagaId(saga.getId())
                .sagaType(saga.getType())
                .status(saga.getStatus())
                .currentStep(saga.getCurrentStep())
                .createdAt(saga.getCreatedAt())
                .updatedAt(saga.getUpdatedAt())
                .message("Saga status retrieved successfully")
                .build();
    }
    public void handleProcesoCreationFailed(ProcesoCreationFailedEvent event) {

        SagaEntity saga = sagaRepository.findById(event.getSagaId())
                .orElseThrow(() -> new ProcesoSagaException(SAGA_NOT_FOUND));

        stateMachine.validateTransition(
                saga.getType(),
                saga.getStatus(),
                SagaStatus.FAILED
        );

        saga.setStatus(SagaStatus.FAILED);

        sagaRepository.save(saga);
    }


    public void handleProcesoComiteNotificated(ProcesoComiteCreatedEvent event) {

        SagaEntity saga = sagaRepository.findById(event.getSagaId())
                .orElseThrow(() -> new ProcesoSagaException(SAGA_NOT_FOUND));


        saga.setStatus(SagaStatus.COMITE_NOTIFICATED);

        sagaRepository.save(saga);

        saga.setStatus(SagaStatus.COMPLETED);

        sagaRepository.save(saga);

    }

}