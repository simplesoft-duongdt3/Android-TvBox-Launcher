package duongmh3.launcher.common.list;

import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

/**
 * Created by duongmatheo on 7/12/17.
 */

public class RecyclerViewHolder extends RecyclerView.ViewHolder {
    public RecyclerViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent, @LayoutRes int layoutId) {
        super(inflater.inflate(layoutId, parent, false));
    }
}
