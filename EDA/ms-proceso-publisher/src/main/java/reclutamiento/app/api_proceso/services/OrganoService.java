package reclutamiento.app.api_proceso.services;

import reclutamiento.app.api_proceso.resources.dtos.request.OrganoRequest;
import reclutamiento.app.api_proceso.resources.dtos.response.OrganoResponse;
import reclutamiento.app.api_proceso.resources.dtos.response.PaginatedResponse;

import java.util.UUID;

public interface OrganoService {
    PaginatedResponse<OrganoResponse> getOrganoByPage(
            String filtro,
            int size,
            int page,
            String orderBy,
            String orderDir
    );

    OrganoResponse getById(UUID id);

    OrganoResponse create(OrganoRequest request);

    OrganoResponse update(OrganoRequest request, UUID id);

    void delete(UUID id);
}
