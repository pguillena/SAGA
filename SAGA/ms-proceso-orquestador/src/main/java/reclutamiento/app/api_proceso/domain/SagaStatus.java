package reclutamiento.app.api_proceso.domain;

public enum SagaStatus {

    STARTED,
    PROCESO_CREATED,
    COMITE_CREATED,
    COMITE_NOTIFICATED,
    COMPLETED,
    FAILED,
    COMPENSATING,
    COMPENSATED
}