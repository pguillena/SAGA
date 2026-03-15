package reclutamiento.app.api_proceso.resources.dtos.response;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.math.BigDecimal;
import java.util.UUID;

public record ProcesoComiteResponse(
        UUID id,
        @JsonIgnore // Evitar error StackOverflow
        ProcesoResponse proceso,
        OrganoResponse organo,
        String nombre,
        BigDecimal tipo,
        String email
) {
}
