package reclutamiento.app.api_proceso.factory;

import org.springframework.stereotype.Component;
import reclutamiento.app.api_proceso.resources.dtos.commands.CreateProcesoComiteCommand;
import reclutamiento.app.api_proceso.resources.dtos.commands.CreateProcesoCommand;
import reclutamiento.app.api_proceso.resources.dtos.request.ProcesoRequest;
import reclutamiento.app.api_proceso.resources.dtos.saga.CreateProcesoSagaPayload;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
public class SagaCommandFactory {

    public CreateProcesoCommand buildCreateProcesoCommand(
            ProcesoRequest request,
            UUID sagaId) {

        return CreateProcesoCommand.builder()
                .eventId(UUID.randomUUID())
                .sagaId(sagaId)
                .nombre(request.nombre())
                .resumen(request.resumen())
                .titulo(request.titulo())
                .cantidad(request.cantidad())
                .fechaInicio(request.fechaInicio())
                .fechaFin(request.fechaFin())
                .organoId(request.organoId())
                .timestamp(LocalDateTime.now())
                .listaProcesoComite(request.listaProcesoComite())
                .build();
    }

    public CreateProcesoComiteCommand buildCreateProcesoComiteCommand(
            UUID sagaId,
            UUID procesoId,
            CreateProcesoSagaPayload payload) {

        return CreateProcesoComiteCommand.builder()
                .eventId(UUID.randomUUID())
                .sagaId(sagaId)
                .procesoId(procesoId)
                .timestamp(LocalDateTime.now())
                .listaProcesoComite(payload.getListaProcesoComite())
                .build();
    }

    public CreateProcesoComiteCommand buildNotificationProcesoComiteCommand(
            UUID sagaId,
            UUID procesoId,
            CreateProcesoSagaPayload payload) {

        return CreateProcesoComiteCommand.builder()
                .eventId(UUID.randomUUID())
                .sagaId(sagaId)
                .procesoId(procesoId)
                .timestamp(LocalDateTime.now())
                .listaProcesoComite(payload.getListaProcesoComite())
                .build();
    }
}