package reclutamiento.app.api_proceso.mongodb.adapter;

import reclutamiento.app.api_proceso.command.dto.message.ProcesoComiteMessage;
import reclutamiento.app.api_proceso.entities.ProcesoComiteEntity;
import reclutamiento.app.api_proceso.mongodb.command.ProcesoComiteCreateCommand;
import reclutamiento.app.api_proceso.mongodb.document.ProcesoComiteDocument;

import java.util.List;

public interface ProcesoComiteAdapter {

    List<ProcesoComiteCreateCommand> toListCommand(List<ProcesoComiteMessage> procesoMessage);

    List<ProcesoComiteDocument> toListDocument(List<ProcesoComiteCreateCommand> command);

    List<ProcesoComiteCreateCommand> entityToListCommand(List<ProcesoComiteEntity> listaProcesoComiteEntity);
}
