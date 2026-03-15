package reclutamiento.app.api_proceso.rabbitmq.suscriber;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import reclutamiento.app.api_proceso.rabbitmq.message.ProcesoMessage;
import reclutamiento.app.api_proceso.service.EmailService;

@Slf4j
@RequiredArgsConstructor
@Component
public class ProcesoMessageServiceImpl implements ProcesoMessageService {
    private final EmailService emailService;
    @RabbitListener(queues = "${rabbitmq.queue.proceso.notificacion}")
    @Override
    public void read(ProcesoMessage procesoMessage) throws SubscriberException {
        try {
            log.info("Proceso => {}", procesoMessage);
            emailService.sendEmail(procesoMessage);
        } catch (Exception e) {
            e.printStackTrace();
            throw new SubscriberException(e);
        }
    }
}
