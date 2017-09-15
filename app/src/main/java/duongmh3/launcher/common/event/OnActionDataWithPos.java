package duongmh3.launcher.common.event;

/**
 * Created by doanthanhduong on 12/12/16.
 */
@FunctionalInterface
public interface OnActionDataWithPos<T> {
    void onAction(T data, int pos);
}
