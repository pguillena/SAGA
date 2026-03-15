package reclutamiento.app.api_proceso.domain;


import org.springframework.stereotype.Component;
import reclutamiento.app.api_proceso.services.ProcesoSagaException;

import java.util.Map;
import java.util.Set;

import static reclutamiento.app.api_proceso.messages.ProductSagaMessages.ERROR_SAGA_STATE_INVALID;
import static reclutamiento.app.api_proceso.messages.ProductSagaMessages.ERROR_UNSUPPORTED_SAGA_TYPE;

@Component
public class SagaStateMachine {

    private static final Map<SagaStatus, Set<SagaStatus>> CREATE_PRODUCT_FLOW =
            Map.of(
                    SagaStatus.STARTED, Set.of(SagaStatus.PROCESO_CREATED, SagaStatus.FAILED),
                    SagaStatus.PROCESO_CREATED, Set.of(SagaStatus.COMITE_CREATED, SagaStatus.COMPENSATING),
                    SagaStatus.COMITE_CREATED, Set.of(SagaStatus.COMPLETED),
                    SagaStatus.COMPENSATING, Set.of(SagaStatus.COMPENSATED)
            );

    public void validateTransition(SagaType type,
                                   SagaStatus current,
                                   SagaStatus next) {

        Map<SagaStatus, Set<SagaStatus>> flow = resolveFlow(type);

        Set<SagaStatus> allowed = flow.get(current);

        if (allowed == null || !allowed.contains(next)) {
            throw new ProcesoSagaException(ERROR_SAGA_STATE_INVALID);
        }
    }

    private Map<SagaStatus, Set<SagaStatus>> resolveFlow(SagaType type) {

        return switch (type) {
            case CREATE_PROCESO_WITH_COMITE -> CREATE_PRODUCT_FLOW;
            /*case // TODO*/
            default -> throw new ProcesoSagaException(ERROR_UNSUPPORTED_SAGA_TYPE);
        };
    }
}