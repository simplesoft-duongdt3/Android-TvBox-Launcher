package duongmh3.launcher.common.list;

import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import butterknife.ButterKnife;

/**
 * Created by duongmatheo on 7/12/17.
 */

public class RecyclerButterKnifeViewHolder extends RecyclerViewHolder {
    public RecyclerButterKnifeViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent, @LayoutRes int layoutId) {
        super(inflater, parent, layoutId);
        injectView();
    }

    private void injectView() {
        ButterKnife.bind(this, this.itemView);
    }
}
