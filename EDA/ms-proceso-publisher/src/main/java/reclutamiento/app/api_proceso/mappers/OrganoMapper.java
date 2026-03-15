package reclutamiento.app.api_proceso.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import reclutamiento.app.api_proceso.entities.OrganoEntity;
import reclutamiento.app.api_proceso.resources.dtos.request.OrganoRequest;
import reclutamiento.app.api_proceso.resources.dtos.response.OrganoResponse;

@Mapper(componentModel = "spring")
public interface OrganoMapper {

    OrganoResponse toResponse(OrganoEntity entity);

    OrganoEntity toEntity(OrganoRequest request);

    void entityToUpdate(OrganoRequest request, @MappingTarget OrganoEntity entity);
}
