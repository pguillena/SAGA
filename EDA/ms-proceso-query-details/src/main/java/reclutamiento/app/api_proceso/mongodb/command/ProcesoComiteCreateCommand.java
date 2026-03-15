package reclutamiento.app.api_proceso.mongodb.command;

import lombok.Builder;

import java.math.BigDecimal;
import java.util.UUID;

@Builder
public record ProcesoComiteCreateCommand(
         UUID id,
         OrganoCreateCommand organo,
         BigDecimal tipo,
         String nombre,
         String email

) {
}
