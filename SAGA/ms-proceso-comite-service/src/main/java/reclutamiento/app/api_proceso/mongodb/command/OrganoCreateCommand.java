package reclutamiento.app.api_proceso.mongodb.command;

import lombok.Builder;

import java.util.UUID;

@Builder
public record OrganoCreateCommand(
        UUID id,
        String codigo,
        String nombre,
        String sigla

) {
}
