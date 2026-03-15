package reclutamiento.app.api_proceso.domain;

import lombok.Getter;
import reclutamiento.app.api_proceso.entities.SagaEntity;
import reclutamiento.app.api_proceso.services.ProcesoSagaException;

import static reclutamiento.app.api_proceso.messages.ProductSagaMessages.ERROR_SAGA_STATE_INVALID;

@Getter
public class SagaDomain {

    private final SagaEntity saga;

    public SagaDomain(SagaEntity saga) {
        this.saga = saga;
    }

    public void transitionTo(SagaStatus newStatus) {

        if (!isValidTransition(saga.getStatus(), newStatus)) {
            throw new ProcesoSagaException(ERROR_SAGA_STATE_INVALID);
        }

        saga.setStatus(newStatus);
    }

    private boolean isValidTransition(SagaStatus current, SagaStatus next) {

        return switch (current) {
            case STARTED -> next == SagaStatus.PROCESO_CREATED
                    || next == SagaStatus.FAILED;

            case PROCESO_CREATED -> next == SagaStatus.COMITE_CREATED
                    || next == SagaStatus.COMPENSATING;

            case COMITE_CREATED -> next == SagaStatus.COMPLETED;

            case COMPENSATING -> next == SagaStatus.COMPENSATED;

            default -> false;
        };
    }
}