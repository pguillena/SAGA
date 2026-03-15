package reclutamiento.app.api_proceso.publisher.listener;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import reclutamiento.app.api_proceso.publisher.message.ProcesoMessage;
import reclutamiento.app.api_proceso.publisher.rabbit.ProcesoPublisherService;

@Slf4j
@RequiredArgsConstructor
@Component
public class ProcesoListener {
    private final ProcesoPublisherService procesoPublisherService;

    @EventListener
    @Async
    public void handleAsyncProcesoEvent(ProcesoMessage procesoMessage) {
        log.info("Proceso Message => {}",procesoMessage);
        procesoPublisherService.send(procesoMessage);
    }
}
