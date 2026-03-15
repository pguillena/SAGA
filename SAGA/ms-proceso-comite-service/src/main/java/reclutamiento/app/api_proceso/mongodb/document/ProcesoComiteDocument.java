package reclutamiento.app.api_proceso.mongodb.document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;
import java.math.BigDecimal;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;
import java.util.UUID;
import org.springframework.data.mongodb.core.index.Indexed;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(value =  "proceso-comite-resumen")
@Data
public class ProcesoComiteDocument {
    @MongoId(FieldType.OBJECT_ID)
    private String id;

    @Indexed
    @Field("id_proceso_comite")
    private UUID idProcesoComite;
    @Field("organo")
    private OrganoDocument organo;
    @Field("tipo")
    private BigDecimal tipo;
    @Field("nombre")
    private String nombre;
    @Field("email")
    private String email;
    @Field("fecha_creacion")
    private LocalDateTime fechaCreacion;
}
