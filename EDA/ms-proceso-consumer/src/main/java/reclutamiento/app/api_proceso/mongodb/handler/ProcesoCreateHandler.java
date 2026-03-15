package reclutamiento.app.api_proceso.mongodb.handler;

import reclutamiento.app.api_proceso.mongodb.command.ProcesoCreateCommand;

public interface ProcesoCreateHandler {

    void create(ProcesoCreateCommand command);
}
