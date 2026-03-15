package reclutamiento.app.api_proceso.domain;

public enum SagaType {

    CREATE_PROCESO_WITH_COMITE,
    CHANGE_PROCESO_COMITE,
    DELETE_PROCESO,
    UPDATE_PROCESO;

    public boolean requiresPriceService() {
        return this == CREATE_PROCESO_WITH_COMITE ||
                this == CHANGE_PROCESO_COMITE;
    }
}