package reclutamiento.app.api_proceso.resources.dtos.events;

import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProcesoComiteCreatedEvent {

    private UUID eventId;
    private UUID sagaId;
    private UUID procesoId;
    private LocalDateTime timestamp;
}