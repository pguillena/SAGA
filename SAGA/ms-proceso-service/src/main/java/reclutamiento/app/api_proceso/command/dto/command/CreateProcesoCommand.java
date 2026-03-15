package reclutamiento.app.api_proceso.command.dto.command;

import lombok.*;
import reclutamiento.app.api_proceso.command.dto.ProcesoComiteRequest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;



@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateProcesoCommand {

    private UUID eventId;
    private UUID sagaId;

    private String nombre;
    private String resumen;
    private String titulo;
    private BigDecimal cantidad;

    private LocalDate fechaInicio;
    private LocalDate fechaFin;

    private UUID organoId;

    private LocalDateTime timestamp;
    List<ProcesoComiteRequest> listaProcesoComite;
}