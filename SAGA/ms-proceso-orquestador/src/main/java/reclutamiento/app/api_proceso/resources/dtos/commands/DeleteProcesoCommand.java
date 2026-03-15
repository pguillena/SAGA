package reclutamiento.app.api_proceso.resources.dtos.commands;

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