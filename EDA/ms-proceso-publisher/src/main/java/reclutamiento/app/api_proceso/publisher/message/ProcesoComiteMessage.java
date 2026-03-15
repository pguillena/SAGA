package reclutamiento.app.api_proceso.publisher.message;
import lombok.Data;
import reclutamiento.app.api_proceso.entities.OrganoEntity;
import reclutamiento.app.api_proceso.entities.ProcesoEntity;

import java.math.BigDecimal;
import java.util.UUID;

@Data
public class ProcesoComiteMessage {

    private UUID id;
    private OrganoMessage organo;
    private BigDecimal tipo;
    private String nombre;
    private String email;
}
