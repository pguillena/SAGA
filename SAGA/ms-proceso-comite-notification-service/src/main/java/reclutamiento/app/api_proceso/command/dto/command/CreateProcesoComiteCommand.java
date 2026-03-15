package reclutamiento.app.api_proceso.command.dto.command;

import lombok.*;
import reclutamiento.app.api_proceso.command.dto.ProcesoComiteRequest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;



@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateProcesoComiteCommand {

    private UUID eventId;
    private UUID sagaId;
    private UUID procesoId;
    private LocalDateTime timestamp;
    List<ProcesoComiteRequest> listaProcesoComite;
}