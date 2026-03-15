package reclutamiento.app.api_proceso.rabbitmq.suscriber;

import reclutamiento.app.api_proceso.rabbitmq.message.ProcesoMessage;

public interface ProcesoMessageService {
    void read(ProcesoMessage procesoMessage) throws SubscriberException;

}
