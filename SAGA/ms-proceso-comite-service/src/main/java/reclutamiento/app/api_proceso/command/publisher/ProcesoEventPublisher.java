package reclutamiento.app.api_proceso.command.publisher;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;
import reclutamiento.app.api_proceso.command.dto.ProcesoComiteRequest;
import reclutamiento.app.api_proceso.command.dto.event.ProcesoComiteCreatedEvent;
import reclutamiento.app.api_proceso.command.dto.event.ProcesoComiteCreationFailedEvent;
import reclutamiento.app.api_proceso.entities.ProcesoComiteEntity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;


@Component
@RequiredArgsConstructor
public class ProcesoEventPublisher {

    private final RabbitTemplate rabbitTemplate;

    private static final String EXCHANGE = "saga.exchange";

    public void publishProcesoComiteCreated(UUID sagaId, UUID procesoId) {

        ProcesoComiteCreatedEvent event = ProcesoComiteCreatedEvent.builder()
                .eventId(UUID.randomUUID())
                .sagaId(sagaId)
                .procesoId(procesoId)
                .timestamp(LocalDateTime.now())
                .build();

        rabbitTemplate.convertAndSend(
                EXCHANGE,
                "proceso.comite.created",
                event
        );
    }

    public void publishProcesoComiteCreationFailed(UUID sagaId, String reason) {

        ProcesoComiteCreationFailedEvent event =
                ProcesoComiteCreationFailedEvent
                        .builder()
                        .eventId(UUID.randomUUID())
                        .sagaId(sagaId)
                        .reason(reason)
                        .timestamp(LocalDateTime.now())
                        .build();
        rabbitTemplate.convertAndSend(
                EXCHANGE,
                "proceso.comite.creation.failed",
                event
        );
    }
}