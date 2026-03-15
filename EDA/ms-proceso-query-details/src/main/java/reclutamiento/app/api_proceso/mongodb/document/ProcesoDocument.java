package reclutamiento.app.api_proceso.mongodb.document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(value =  "proceso-resumen")
@Data
public class ProcesoDocument {
    @MongoId(FieldType.OBJECT_ID)
    private String id;

    @Indexed
    @Field("id_proceso")
    private UUID idProceso;

    @Field("nombre")
    private String nombre;
    @Field("resumen")
    private String resumen;
    @Field("titulo")
    private String titulo;

    @Field("cantidad")
    private BigDecimal cantidad;

    @Field("fecha_inicio")
    private LocalDate fechaInicio;
    @Field("fecha_fin")
    private LocalDate fechaFin;
    @Field("estado")
    private String estado;
    @Field("fecha_creacion")
    private LocalDateTime fechaCreacion;

    @Field("organo")
    private OrganoDocument organo;

    @Field("listaProcesoComite")
    private List<ProcesoComiteDocument> listaProcesoComite;
}
