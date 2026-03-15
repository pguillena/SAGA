package reclutamiento.app.api_proceso.services;

import reclutamiento.app.api_proceso.resources.dtos.request.OrganoRequest;
import reclutamiento.app.api_proceso.resources.dtos.request.ProcesoRequest;
import reclutamiento.app.api_proceso.resources.dtos.response.OrganoResponse;
import reclutamiento.app.api_proceso.resources.dtos.response.PaginatedResponse;
import reclutamiento.app.api_proceso.resources.dtos.response.ProcesoResponse;

import java.util.Optional;
import java.util.UUID;

public interface ProcesoService {

    PaginatedResponse<ProcesoResponse> getProcesoByPage(
            String filtro,
            int size,
            int page,
            String orderBy,
            String orderDir
    );

    Optional<ProcesoResponse> getById(UUID id);

    ProcesoResponse create(ProcesoRequest request);

    ProcesoResponse update(ProcesoRequest request, UUID id);

    void delete(UUID id);

}
