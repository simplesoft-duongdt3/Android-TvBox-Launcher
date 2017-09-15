package duongmh3.launcher.common.list;

import android.support.annotation.Nullable;

import java.util.ArrayList;

/**
 * Created by admin on 7/14/17.
 */

public class RecyclerItems<T extends RecyclerViewModel> extends ArrayList<T> {
    @Nullable
    public T getNullableAt(int pos) {
        T data = null;
        if (pos >= 0 && pos < size()) {
            data = get(pos);
        }
        return data;
    }
}
