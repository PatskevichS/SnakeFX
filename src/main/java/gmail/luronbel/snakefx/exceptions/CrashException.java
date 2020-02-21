package gmail.luronbel.snakefx.exceptions;

public class CrashException extends Exception {
    public CrashException() {
    }

    public CrashException(final String message) {
        super(message);
    }

    public CrashException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public CrashException(final Throwable cause) {
        super(cause);
    }
}
