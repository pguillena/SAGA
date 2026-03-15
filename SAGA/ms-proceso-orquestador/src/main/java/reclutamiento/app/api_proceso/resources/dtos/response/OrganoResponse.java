package reclutamiento.app.api_proceso.resources.dtos.response;
import java.util.UUID;

public record OrganoResponse (
        UUID id,
        String codigo,
        String nombre,
        String sigla
) {
}