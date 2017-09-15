package duongmh3.launcher.common;

/**
 * Created by duongmatheo on 7/12/17.
 */

public class AppException extends Exception {
    public AppException(String message) {
        super(message);
    }

    public AppException(String message, Throwable cause) {
        super(message, cause);
    }

    public AppException(Throwable cause) {
        super(cause);
    }
}
