package reclutamiento.app.api_proceso.messages;

public class ProductSagaMessages {

    private ProductSagaMessages() {
    }

    public static final String ERROR_SERIALIZING_PAYLOAD ="Error serializing saga payload";

    public static final String ERROR_DESERIALIZING_PAYLOAD ="Error deserializing saga payload";

    public static final String SAGA_NOT_FOUND ="Saga instance not found";

    public static final String ERROR_SAGA_STATE_INVALID ="Invalid saga state transition";

    public static final String ERROR_UNSUPPORTED_SAGA_TYPE ="Unsupported saga type";
}
