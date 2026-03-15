package reclutamiento.app.api_proceso.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@Entity
@Table(name = "tb_proceso_comite")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProcesoComiteEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "proceso_id")
    private ProcesoEntity proceso;

    @ManyToOne
    @JoinColumn(name = "organo_id")
    private OrganoEntity organo;

    private BigDecimal tipo;
    private String nombre;
    private String email;

    @Column(name = "fecha_creacion")
    private LocalDateTime fechaCreacion;

    @Column(name = "fecha_modificacion")
    private LocalDateTime fechaModificacion;
}
