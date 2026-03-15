package reclutamiento.app.api_proceso.command.dto.command;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeleteProcesoCommand {
    private UUID sagaId;
    private UUID procesoId;
}
