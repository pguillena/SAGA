package reclutamiento.app.api_proceso.publisher;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;
import reclutamiento.app.api_proceso.resources.dtos.commands.CreateProcesoComiteCommand;
import reclutamiento.app.api_proceso.resources.dtos.commands.CreateProcesoCommand;
import reclutamiento.app.api_proceso.resources.dtos.commands.DeleteProcesoCommand;
import reclutamiento.app.api_proceso.services.ProcesoSagaException;

import static reclutamiento.app.api_proceso.messages.ProductSagaMessages.ERROR_DESERIALIZING_PAYLOAD;

@Component
@RequiredArgsConstructor
public class SagaCommandPublisher {

    private final RabbitTemplate rabbitTemplate;

    private static final String EXCHANGE = "saga.exchange";

    public void sendCreateProcesoCommand(CreateProcesoCommand command) {

        rabbitTemplate.convertAndSend(
                EXCHANGE,
                "proceso.create",
                command
        );
    }

    public void sendCreateProcesoComiteCommand(CreateProcesoComiteCommand command) {

        try {
            rabbitTemplate.convertAndSend(
                    EXCHANGE,
                    "proceso.comite.create",
                    command
            );
        }
        catch (Exception e) {
            throw new ProcesoSagaException(ERROR_DESERIALIZING_PAYLOAD, e);
        }

    }

    public void sendNotificationProcesoComiteCommand(CreateProcesoComiteCommand command) {

        try {
            rabbitTemplate.convertAndSend(
                    EXCHANGE,
                    "proceso.comite.notification",
                    command
            );
        }
        catch (Exception e) {
            throw new ProcesoSagaException(ERROR_DESERIALIZING_PAYLOAD, e);
        }

    }

    public void sendDeleteProcesoCommand(java.util.UUID  ProcesoId, java.util.UUID sagaId) {

        rabbitTemplate.convertAndSend(
                EXCHANGE,
                "proceso.delete",
                new DeleteProcesoCommand(sagaId, ProcesoId)
        );
    }
}
