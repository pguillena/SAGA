package reclutamiento.app.api_proceso.mongodb.document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Field;

import java.math.BigDecimal;
import java.util.UUID;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProcesoComiteDocument {

    @Field("id_proceso_comite")
    private UUID id;
    @Field("organo")
    private OrganoDocument organo;
    @Field("tipo")
    private BigDecimal tipo;
    @Field("nombre")
    private String nombre;
    @Field("email")
    private String email;
}
