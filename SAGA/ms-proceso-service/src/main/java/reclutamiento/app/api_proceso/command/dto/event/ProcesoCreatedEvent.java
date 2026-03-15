package reclutamiento.app.api_proceso.command.dto.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import reclutamiento.app.api_proceso.command.dto.ProcesoComiteRequest;
import reclutamiento.app.api_proceso.entities.ProcesoComiteEntity;

import java.util.List;
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
    private List<ProcesoComiteRequest> listaProcesoComite;

}