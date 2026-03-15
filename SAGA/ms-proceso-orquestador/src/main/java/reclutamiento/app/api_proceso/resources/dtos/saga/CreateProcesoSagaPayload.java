package reclutamiento.app.api_proceso.resources.dtos.saga;

import lombok.*;
import reclutamiento.app.api_proceso.resources.dtos.request.ProcesoComiteRequest;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateProcesoSagaPayload {
    List<ProcesoComiteRequest> listaProcesoComite;
}