package reclutamiento.app.api_proceso.mongodb.adapter;

import reclutamiento.app.api_proceso.mongodb.command.ProcesoCreateCommand;
import reclutamiento.app.api_proceso.mongodb.document.ProcesoDocument;
import reclutamiento.app.api_proceso.rabbitmq.message.ProcesoMessage;

public interface ProcesoAdapter {

    ProcesoCreateCommand toCommand(ProcesoMessage procesoMessage);

    ProcesoDocument toDocument(ProcesoCreateCommand command);
}
