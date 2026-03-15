package reclutamiento.app.api_proceso.resources.dtos.events;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class ProcesoCreatedEvent extends BaseEvent {

    private UUID procesoId;
    private String titulo;
    private UUID organoId;

}