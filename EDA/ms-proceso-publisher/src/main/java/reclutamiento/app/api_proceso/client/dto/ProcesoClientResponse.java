package reclutamiento.app.api_proceso.client.dto;
import java.time.LocalDate;
import java.util.UUID;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ProcesoClientResponse(
        UUID id,
        String nombre,
        String resumen,
        String titulo,
        LocalDate fechaInicio,
        LocalDate fechaFin,
        String estado
) {

}
