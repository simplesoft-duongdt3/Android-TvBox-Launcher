package duongmh3.launcher.common.list;

import android.widget.TextView;

import me.drakeet.multitype.ItemViewBinder;

/**
 * Created by admin on 7/14/17.
 */

public abstract class ViewHolderBinder<T, VH extends RecyclerViewHolder> extends ItemViewBinder<T, VH> {

    public void setText(TextView tv, CharSequence charSequence) {
        if (tv != null) {
            tv.setText(charSequence);
        }
    }
}
