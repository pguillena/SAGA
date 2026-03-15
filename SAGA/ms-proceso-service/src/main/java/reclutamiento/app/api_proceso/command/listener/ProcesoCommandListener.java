package reclutamiento.app.api_proceso.command.listener;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import reclutamiento.app.api_proceso.command.dto.command.CreateProcesoCommand;
import reclutamiento.app.api_proceso.command.dto.command.DeleteProcesoCommand;
import reclutamiento.app.api_proceso.command.service.ProcesoCommandService;

@Component
@RequiredArgsConstructor
public class ProcesoCommandListener {

    private final ProcesoCommandService procesoCommandService;

    @RabbitListener(queues = "proceso.create.queue")
    public void handleCreateProceso(CreateProcesoCommand command) {
        procesoCommandService.createProceso(command);
    }

    @RabbitListener(queues = "proceso.delete.queue")
    public void handleDeleteProceso(DeleteProcesoCommand command) {
        procesoCommandService.deleteProceso(command);
    }
}