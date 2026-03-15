package reclutamiento.app.api_proceso.mappers;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import reclutamiento.app.api_proceso.entities.ProcesoComiteEntity;
import reclutamiento.app.api_proceso.resources.dtos.response.ProcesoComiteResponse;

@Mapper(
        componentModel = "spring",
        uses = {ProcesoMapper.class}
)
public interface ProcesoComiteMapper {
    @Mapping(target = "proceso", ignore = true)
    ProcesoComiteResponse toResponse(ProcesoComiteEntity entity);
}
