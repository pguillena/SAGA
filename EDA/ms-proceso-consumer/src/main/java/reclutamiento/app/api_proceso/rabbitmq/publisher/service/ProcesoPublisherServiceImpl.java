package reclutamiento.app.api_proceso.rabbitmq.publisher.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import reclutamiento.app.api_proceso.mongodb.command.ProcesoCreateCommand;

@Slf4j
@RequiredArgsConstructor
@Service
public class ProcesoPublisherServiceImpl implements ProcesoPublisherService {

    private final RabbitTemplate rabbitTemplate;
    private final Queue queueProcesoNotificacion;

    @Override
    public void send(ProcesoCreateCommand procesoCreateCommand) {
        log.info("ProcesoPublisherService::Message =>{}",procesoCreateCommand);
        rabbitTemplate.convertAndSend(queueProcesoNotificacion.getName(), procesoCreateCommand);
        log.info("ProcesoPublisherService::Mensaje publicado");
    }
}
