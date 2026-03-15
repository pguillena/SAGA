package reclutamiento.app.api_proceso.rabbitmq.suscriber;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import reclutamiento.app.api_proceso.mongodb.adapter.ProcesoAdapter;
import reclutamiento.app.api_proceso.mongodb.handler.ProcesoCreateHandler;
import reclutamiento.app.api_proceso.rabbitmq.message.ProcesoMessage;

@Slf4j
@RequiredArgsConstructor
@Component
public class ProcesoMessageServiceImpl implements ProcesoMessageService {

    private final ProcesoCreateHandler procesoCreateHandler;
    private final ProcesoAdapter procesoAdapter;

    @RabbitListener(queues = "${rabbitmq.queue.proceso.nuevo}")
    @Override
    public void read(ProcesoMessage procesoMessage) throws SubscriberException {
        try {
            log.info("Proceso => {}", procesoMessage);
            procesoCreateHandler.create(procesoAdapter.toCommand(procesoMessage));
        } catch (Exception e) {
            e.printStackTrace();
            throw new SubscriberException(e);
        }
    }
}
