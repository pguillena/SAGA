package reclutamiento.app.api_proceso.services.Impl;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reclutamiento.app.api_proceso.mappers.OrganoMapper;
import reclutamiento.app.api_proceso.repositories.OrganoRepository;
import reclutamiento.app.api_proceso.resources.dtos.request.OrganoRequest;
import reclutamiento.app.api_proceso.resources.dtos.response.OrganoResponse;
import reclutamiento.app.api_proceso.resources.dtos.response.PaginatedResponse;
import reclutamiento.app.api_proceso.services.OrganoService;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@AllArgsConstructor
public class OrganoServiceImpl implements OrganoService {

    private final OrganoRepository organoRepository;
    private final OrganoMapper organoMapper;

    @Override
    public PaginatedResponse<OrganoResponse> getOrganoByPage(String filtro, int size, int page, String orderBy, String orderDir) {
        // orderDir: asc | desc
        var sort = orderDir.equalsIgnoreCase("desc")
                ? Sort.by(orderBy).descending()
                : Sort.by(orderBy).ascending();

        var pageable = PageRequest.of(page, size, sort);

        var pageResult = organoRepository.getOrganoByPage(filtro, pageable);

        var content = pageResult
                .getContent()
                .stream()
                .map(organoMapper::toResponse)
                .toList();

        return new PaginatedResponse<>(content, pageResult.getTotalElements(), page, size);
    }

    @Override
    public OrganoResponse getById(UUID id) {
        return organoRepository.findById(id)
                .map(organoMapper::toResponse)
                .orElse(null);
    }

    @Override
    @Transactional
    public OrganoResponse create(OrganoRequest request) {
        var entity = organoMapper.toEntity(request);
        entity.setFechaCreacion(LocalDateTime.now());
        return organoMapper.toResponse(organoRepository.save(entity));
    }

    @Override
    @Transactional
    public OrganoResponse update(OrganoRequest request, UUID id) {
        var clienteEntity = organoRepository.findById(id)
                .map(entity -> {
                    organoMapper.entityToUpdate(request, entity);
                    entity.setFechaModificacion(LocalDateTime.now());
                    return organoRepository.save(entity);
                })
                .orElseThrow(() -> new RuntimeException("Cliente con ID " + id + "no existe."));

        return organoMapper.toResponse(clienteEntity);
    }

    @Override
    @Transactional
    public void delete(UUID id) {
        organoRepository.deleteById(id);
    }

}
