package reclutamiento.app.api_proceso.resources.dtos.request;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public record ProcesoRequest(

        @NotBlank(message = "El nombre del proceso es obligatorio")
        String nombre,

        @NotBlank(message = "El resumen es obligatorio")
        String resumen,

        @NotBlank(message = "El título es obligatorio")
        String titulo,

        @NotNull(message = "La cantidad es obligatoria")
        @Min(value = 1, message = "La cantidad debe ser mayor o igual a 1")
        BigDecimal cantidad,

        @NotNull(message = "Debe ingresar una fecha de inicio válida")
        LocalDate fechaInicio,

        @NotNull(message = "Debe ingresar una fecha de fin válida")
        LocalDate fechaFin,

        @NotNull(message = "El órgano es obligatorio")
        UUID organoId,

        @Valid
        @NotEmpty(message = "Debe registrar al menos un comité de evaluación")
        List<ProcesoComiteRequest> listaProcesoComite


        ) {

}
