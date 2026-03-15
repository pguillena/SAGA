package reclutamiento.app.api_proceso.publisher.rabbit;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import reclutamiento.app.api_proceso.publisher.message.ProcesoMessage;

@Slf4j
@RequiredArgsConstructor
@Service
public class ProcesoPublisherServiceImpl implements ProcesoPublisherService {

    private final RabbitTemplate rabbitTemplate;
    private final Queue queueProcesoNuevo;

    @Override
    public void send(ProcesoMessage procesoMessage) {
        log.info("ProcesoPublisherService::Message =>{}",procesoMessage);
        rabbitTemplate.convertAndSend(queueProcesoNuevo.getName(), procesoMessage);
        log.info("ProcesoPublisherService::Mensaje publicado");
    }
}
