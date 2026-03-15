package reclutamiento.app.api_proceso.mongodb.handler;


import reclutamiento.app.api_proceso.mongodb.command.ProcesoComiteCreateCommand;

import java.util.List;

public interface ProcesoComiteCreateHandler {
    void create(List<ProcesoComiteCreateCommand> commands);
}
