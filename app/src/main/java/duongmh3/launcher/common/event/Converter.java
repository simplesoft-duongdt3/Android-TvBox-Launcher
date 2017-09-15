package duongmh3.launcher.common.event;

import android.support.annotation.NonNull;

/**
 * Created by duongmatheo on 5/24/17.
 */

@FunctionalInterface
public interface Converter<I, O> {
    @NonNull
    O convert(@NonNull I input);
}
