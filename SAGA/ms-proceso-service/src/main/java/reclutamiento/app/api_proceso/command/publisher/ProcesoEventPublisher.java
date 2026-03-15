package reclutamiento.app.api_proceso.command.publisher;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;
import reclutamiento.app.api_proceso.command.dto.ProcesoComiteRequest;
import reclutamiento.app.api_proceso.command.dto.event.ProcesoCreatedEvent;
import reclutamiento.app.api_proceso.command.dto.event.ProcesoCreationFailedEvent;
import reclutamiento.app.api_proceso.command.dto.event.ProcesoDeletedEvent;
import reclutamiento.app.api_proceso.entities.ProcesoEntity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;


@Component
@RequiredArgsConstructor
public class ProcesoEventPublisher {

    private final RabbitTemplate rabbitTemplate;

    private static final String EXCHANGE = "saga.exchange";

    public void publishProcesoCreated(ProcesoEntity proceso, UUID sagaId) {

        List<ProcesoComiteRequest> comiteRequests = proceso.getListaProcesoComite()
                .stream()
                .map(c -> new ProcesoComiteRequest(
                        c.getOrgano().getId(),
                        c.getTipo(),
                        c.getNombre(),
                        c.getEmail()
                ))
                .toList();


        ProcesoCreatedEvent event = ProcesoCreatedEvent.builder()
                .eventId(UUID.randomUUID())
                .sagaId(sagaId)
                .titulo(proceso.getTitulo())
                .procesoId(proceso.getId())
                .listaProcesoComite(comiteRequests)
                .timestamp(LocalDateTime.now())
                .build();

        rabbitTemplate.convertAndSend(
                EXCHANGE,
                "proceso.created",
                event
        );
    }

    public void publishProcesoDeleted(UUID procesoId, UUID sagaId) {

        ProcesoDeletedEvent event = ProcesoDeletedEvent.builder()
                .eventId(UUID.randomUUID())
                .sagaId(sagaId)
                .procesoId(procesoId)
                .timestamp(LocalDateTime.now())
                .build();

        rabbitTemplate.convertAndSend(
                EXCHANGE,
                "proceso.deleted",
                event
        );
    }

    public void publishProcesoCreationFailed(UUID sagaId, String reason) {

        ProcesoCreationFailedEvent event =
                ProcesoCreationFailedEvent
                        .builder()
                        .eventId(UUID.randomUUID())
                        .sagaId(sagaId)
                        .reason(reason)
                        .timestamp(LocalDateTime.now())
                        .build();
        rabbitTemplate.convertAndSend(
                EXCHANGE,
                "proceso.creation.failed",
                event
        );
    }
}