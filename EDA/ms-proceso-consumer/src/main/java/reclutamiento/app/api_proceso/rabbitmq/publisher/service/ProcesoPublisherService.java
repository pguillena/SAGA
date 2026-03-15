package reclutamiento.app.api_proceso.rabbitmq.publisher.service;


import reclutamiento.app.api_proceso.mongodb.command.ProcesoCreateCommand;

public interface ProcesoPublisherService {
    void send(ProcesoCreateCommand procesoCreateCommand);
}
