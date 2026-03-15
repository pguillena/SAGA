package reclutamiento.app.api_proceso.mongodb.command;

import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Builder
public record ProcesoCreateCommand(
        UUID id,
         String nombre,
         String resumen,
         String titulo,
         OrganoCreateCommand organo,
         BigDecimal cantidad,
         LocalDate fechaInicio,
         LocalDate fechaFin,
         String estado,
         List<ProcesoComiteCreateCommand>listaProcesoComite

) {
}
