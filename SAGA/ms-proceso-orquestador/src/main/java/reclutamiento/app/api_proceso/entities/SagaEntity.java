package reclutamiento.app.api_proceso.entities;


import jakarta.persistence.*;
import lombok.*;
import reclutamiento.app.api_proceso.domain.SagaStatus;
import reclutamiento.app.api_proceso.domain.SagaType;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "saga")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SagaEntity {

    @Id
    @Column(name = "saga_id", nullable = false, updatable = false)
    private UUID id;

    @Enumerated(EnumType.STRING)
    @Column(name = "saga_type", nullable = false)
    private SagaType type;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private SagaStatus status;

    @Column(name = "current_step")
    private String currentStep;

    //	@Column(name = "payload", columnDefinition = "TEXT")
    private String payload;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;



    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}