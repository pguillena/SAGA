package reclutamiento.app.api_proceso.resources.dtos.saga;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import reclutamiento.app.api_proceso.domain.SagaStatus;
import reclutamiento.app.api_proceso.domain.SagaType;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
public class SagaStatusResponse {

    private UUID sagaId;

    private SagaType sagaType;

    private SagaStatus status;

    private String currentStep;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private String message;
}