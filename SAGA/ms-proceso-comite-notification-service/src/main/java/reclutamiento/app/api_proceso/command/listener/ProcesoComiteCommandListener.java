package reclutamiento.app.api_proceso.command.listener;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import reclutamiento.app.api_proceso.command.dto.command.CreateProcesoComiteCommand;
import reclutamiento.app.api_proceso.command.service.ProcesoComiteCommandService;

@Component
@RequiredArgsConstructor
public class ProcesoComiteCommandListener {

    private final ProcesoComiteCommandService procesoComiteCommandService;

    @RabbitListener(queues = "proceso.comite.notification.queue")
    public void handleNotificacionProcesoComite(CreateProcesoComiteCommand command) {
        procesoComiteCommandService.notificationProcesoComite(command);
    }
}