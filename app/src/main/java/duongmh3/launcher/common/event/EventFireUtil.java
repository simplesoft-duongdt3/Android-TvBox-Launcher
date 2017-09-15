package duongmh3.launcher.common.event;

import android.support.annotation.Nullable;

/**
 * Created by doanthanhduong on 1/5/17.
 */

public class EventFireUtil {

    private EventFireUtil() {
    }

    @Nullable
    public static <P, R> R fireEvent(OnWorkingWithParam<P, R> event, P param) {
        R result = null;
        if (event != null) {
            result = event.doWork(param);
        }
        return result;
    }

    public static <T> void fireEvent(OnActionDataWithPos<T> event, T data, int pos) {
        if (event != null) {
            event.onAction(data, pos);
        }
    }

    public static <T> void fireEvent(OnActionData<T> event, T data) {
        if (event != null) {
            event.onAction(data);
        }
    }

    public static void fireEvent(OnActionNotify event) {
        if (event != null) {
            event.onActionNotify();
        }
    }

    public static <T> T fireEvent(OnWorkingWithException<T> event) throws Exception {
        T result = null;
        if (event != null) {
            result = event.work();
        }
        return result;
    }

    public static void fireEvent(Executable executable) {
        if (executable != null) {
            executable.execute();
        }
    }
}
