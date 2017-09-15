package duongmh3.launcher.common.event;

/**
 * Created by doanthanhduong on 12/14/16.
 */
@FunctionalInterface
public interface OnWorking<T> {
    T work();
}
