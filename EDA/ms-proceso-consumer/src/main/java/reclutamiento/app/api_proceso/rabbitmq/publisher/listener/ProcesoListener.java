package reclutamiento.app.api_proceso.rabbitmq.publisher.listener;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import reclutamiento.app.api_proceso.mongodb.command.ProcesoCreateCommand;
import reclutamiento.app.api_proceso.rabbitmq.publisher.service.ProcesoPublisherService;

@Slf4j
@RequiredArgsConstructor
@Component
public class ProcesoListener {
    private final ProcesoPublisherService procesoPublisherService;

    @EventListener
    @Async
    public void handleAsyncProcesoEvent(ProcesoCreateCommand procesoCreateCommand) {
        log.info("Proceso Notificacion Publisher Message => {}",procesoCreateCommand);
        procesoPublisherService.send(procesoCreateCommand);
    }
}
