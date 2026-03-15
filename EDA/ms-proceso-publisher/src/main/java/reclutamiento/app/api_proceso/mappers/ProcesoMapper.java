package reclutamiento.app.api_proceso.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import reclutamiento.app.api_proceso.entities.OrganoEntity;
import reclutamiento.app.api_proceso.entities.ProcesoEntity;
import reclutamiento.app.api_proceso.publisher.message.ProcesoMessage;
import reclutamiento.app.api_proceso.resources.dtos.request.OrganoRequest;
import reclutamiento.app.api_proceso.resources.dtos.request.ProcesoRequest;
import reclutamiento.app.api_proceso.resources.dtos.response.ProcesoResponse;

@Mapper(
        componentModel = "spring",
        uses = {ProcesoComiteMapper.class}
)
public interface ProcesoMapper {


    @Mapping(source = "organo", target = "organo")
    @Mapping(source = "listaProcesoComite", target = "listaProcesoComite")
    ProcesoResponse toResponse(ProcesoEntity entity);

    @Mapping(source = "listaProcesoComite", target = "listaProcesoComite")
    ProcesoEntity toEntity(ProcesoRequest request);

    @Mapping(source = "organo", target = "organo")
    @Mapping(source = "listaProcesoComite", target = "listaProcesoComite")
    ProcesoMessage toMessage(ProcesoEntity entity);

}
