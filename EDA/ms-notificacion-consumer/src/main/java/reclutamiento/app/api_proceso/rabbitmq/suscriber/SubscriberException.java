package reclutamiento.app.api_proceso.rabbitmq.suscriber;

public class SubscriberException extends Exception {

    private static final long serialVersionUID = -9030085513155905039L;

    public SubscriberException() {
    }

    public SubscriberException(String message) {
        super(message);

    }

    public SubscriberException(Throwable cause) {
        super(cause);
    }

    public SubscriberException(String message, Throwable cause) {
        super(message, cause);
    }

    public SubscriberException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}