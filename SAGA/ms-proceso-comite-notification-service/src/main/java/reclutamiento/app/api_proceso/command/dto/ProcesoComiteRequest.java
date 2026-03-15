package reclutamiento.app.api_proceso.command.dto;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.util.UUID;


public record ProcesoComiteRequest(

        @NotNull(message = "El órgano es obligatorio")
        UUID organoId,

        @NotNull(message = "El tipo de comité es obligatorio")
        @DecimalMin(value = "1", inclusive = true, message = "Debe elegir un tipo de comit")
        BigDecimal tipo,

        @NotBlank(message = "El nombre del representante es obligatorio")
        @Size(min = 5, message = "Debe ingresar nombres y apellidos")
        String nombre,

        @NotBlank(message = "El email es obligatorio")
        @Size(min = 6, message = "Debe ingresar el email")
        @Email(message = "Debe ingresar un email válido")
        String email

) {
}
