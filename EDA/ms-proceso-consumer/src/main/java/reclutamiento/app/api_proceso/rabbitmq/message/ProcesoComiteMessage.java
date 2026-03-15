package reclutamiento.app.api_proceso.rabbitmq.message;
import lombok.Data;

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
