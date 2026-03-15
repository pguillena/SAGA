package reclutamiento.app.api_proceso.resources.dtos.response;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public record ProcesoResponse(
        UUID id,
        String nombre,
        String resumen,
        String titulo,
        LocalDate fechaInicio,
        LocalDate fechaFin,
        OrganoResponse organo,
        String estado,
        List<ProcesoComiteResponse> listaProcesoComite

) {
}
