package reclutamiento.app.api_proceso.command.dto.message;

import lombok.Data;

import java.util.UUID;

@Data
public class OrganoMessage {
    private UUID id;
    private String codigo;
    private String nombre;
    private String sigla;
}
