package pro.sky;

public class IndexWrongException extends RuntimeException {
    public IndexWrongException(String message) {
        super(message);
    }

    public IndexWrongException(String message, Throwable cause) {
        super(message, cause);
    }

    public IndexWrongException(Throwable cause) {
        super(cause);
    }

    public IndexWrongException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public IndexWrongException() {
    }
}
