package reclutamiento.app.api_proceso.listener;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import reclutamiento.app.api_proceso.resources.dtos.events.*;
import reclutamiento.app.api_proceso.services.ProcesoSagaService;

@Component
@RequiredArgsConstructor
public class SagaEventListener {

    private final ProcesoSagaService sagaService;

    @RabbitListener(queues = "proceso.created.queue")
    public void onProcesoCreated(ProcesoCreatedEvent event) {
        sagaService.handleProcesoCreated(event);
    }

    @RabbitListener(queues = "proceso.creation.failed.queue")
    public void onProcesoCreationFailed(ProcesoCreationFailedEvent event) {
        sagaService.handleProcesoCreationFailed(event);
    }

    @RabbitListener(queues = "proceso.deleted.queue")
    public void onProcesoDeleted(ProcesoDeletedEvent event) {
        sagaService.handleProcesoDeleted(event);
    }

    @RabbitListener(queues = "proceso.comite.created.queue")
    public void onProcesoComiteCreated(ProcesoComiteCreatedEvent event) {
        sagaService.handleProcesoComiteCreated(event);
    }

    @RabbitListener(queues = "proceso.comite.notificated.queue")
    public void onProcesoComiteNotificated(ProcesoComiteCreatedEvent event) {
        sagaService.handleProcesoComiteNotificated(event);
    }

    @RabbitListener(queues = "proceso.comite.creation.failed.queue")
    public void onProcesoComiteCreationFailed(ProcesoComiteCreationFailedEvent event) {
        sagaService.handleProcesoComiteCreationFailed(event);
    }

}