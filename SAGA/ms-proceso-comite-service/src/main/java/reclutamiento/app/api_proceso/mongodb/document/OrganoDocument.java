package reclutamiento.app.api_proceso.mongodb.document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.UUID;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrganoDocument {

    @Field("id_organo")
    private UUID id;
    @Field("codigo")
    private String codigo;
    @Field("nombre")
    private String nombre;
    @Field("sigla")
    private String sigla;
}
