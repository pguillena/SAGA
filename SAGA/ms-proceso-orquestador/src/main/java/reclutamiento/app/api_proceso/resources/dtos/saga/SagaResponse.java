package reclutamiento.app.api_proceso.resources.dtos.saga;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@Getter
@AllArgsConstructor
public class SagaResponse {

    private UUID sagaId;
    private String message;
}