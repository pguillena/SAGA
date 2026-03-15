package reclutamiento.app.api_proceso.resources.dtos.request;

import jakarta.validation.constraints.NotEmpty;
import reclutamiento.app.api_proceso.resources.dtos.validators.AlphaNumericWithSpaces;

public record OrganoRequest(
        @NotEmpty(message = "Campo obligatorio")
        String codigo,
        @NotEmpty(message = "Campo obligatorio")
        String nombre,
        @NotEmpty(message = "Campo obligatorio")
        String sigla
) {


}
