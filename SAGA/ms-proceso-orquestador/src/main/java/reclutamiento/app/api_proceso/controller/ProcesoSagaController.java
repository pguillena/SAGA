package reclutamiento.app.api_proceso.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reclutamiento.app.api_proceso.resources.dtos.request.ProcesoRequest;
import reclutamiento.app.api_proceso.resources.dtos.saga.SagaResponse;
import reclutamiento.app.api_proceso.resources.dtos.saga.SagaStatusResponse;
import reclutamiento.app.api_proceso.services.ProcesoSagaService;

import java.util.UUID;

@RestController
@RequestMapping("/api/saga/proceso")
@RequiredArgsConstructor
public class ProcesoSagaController {

    private final ProcesoSagaService sagaService;

    @PostMapping
    public ResponseEntity<SagaResponse> createProceso(
            @Valid @RequestBody ProcesoRequest request) {

        UUID sagaId = sagaService.startCreateProcesoSaga(request);

        return ResponseEntity
                .accepted()
                .body(new SagaResponse(sagaId, "Saga started successfully"));
    }

    @GetMapping("/{sagaId}")
    public ResponseEntity<SagaStatusResponse> getSagaStatus(
            @PathVariable UUID sagaId) {

        return ResponseEntity.ok(
                sagaService.getSagaStatus(sagaId)
        );
    }
}