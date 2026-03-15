package reclutamiento.app.api_proceso.mongodb.handler;

import reclutamiento.app.api_proceso.mongodb.document.ProcesoDocument;
import reclutamiento.app.api_proceso.mongodb.query.ProcesoFiltroRequestDto;

import java.util.List;

public interface ProcesoFindByNombreOrResumenQuery {
    List<ProcesoDocument> findByNombreOrResumenQuery(ProcesoFiltroRequestDto filtroRequestDto);
}
