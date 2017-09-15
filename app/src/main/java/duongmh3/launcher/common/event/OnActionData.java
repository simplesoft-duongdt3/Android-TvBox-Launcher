package duongmh3.launcher.common.event;

import java.io.Serializable;

/**
 * Created by doanthanhduong on 12/12/16.
 */
@FunctionalInterface
public interface OnActionData<T> extends Serializable {
    void onAction(T data);
}
