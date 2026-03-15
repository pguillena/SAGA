package reclutamiento.app.api_proceso.entities;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Builder
@Entity
@Table(name = "tb_proceso")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProcesoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String nombre;
    private String resumen;
    private String titulo;
    @ManyToOne
    @JoinColumn(name = "organo_id")
    private OrganoEntity organo;
    private BigDecimal cantidad;

    @Column(name = "fecha_inicio")
    private LocalDate fechaInicio;
    @Column(name = "fecha_fin")
    private LocalDate fechaFin;
    private String estado;

    @Column(name = "fecha_creacion")
    private LocalDateTime fechaCreacion;

    @Column(name = "fecha_modificacion")
    private LocalDateTime fechaModificacion;

    @OneToMany(mappedBy = "proceso", cascade = CascadeType.ALL)
    private List<ProcesoComiteEntity> listaProcesoComite;
}
