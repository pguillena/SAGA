package reclutamiento.app.api_proceso.mongodb.query;


import lombok.Builder;

@Builder
public record ProcesoFiltroRequestDto (
        String filtro
){
}
