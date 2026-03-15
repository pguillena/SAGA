package reclutamiento.app.api_proceso.publisher.message;

import lombok.Data;
import reclutamiento.app.api_proceso.resources.dtos.response.ProcesoComiteResponse;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
public class ProcesoMessage {

    private UUID id;
    private String nombre;
    private String resumen;
    private String titulo;
    private OrganoMessage organo;
    private BigDecimal cantidad;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private String estado;
    private List<ProcesoComiteResponse> listaProcesoComite;

}
