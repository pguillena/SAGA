package reclutamiento.app.api_proceso.publisher.rabbit;

import reclutamiento.app.api_proceso.publisher.message.ProcesoMessage;

public interface ProcesoPublisherService {
    void send(ProcesoMessage procesoMessage);
}
