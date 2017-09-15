package duongmh3.launcher.func.listapp;

import android.graphics.drawable.Drawable;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatImageView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import duongmh3.launcher.R;
import duongmh3.launcher.common.DataResult;
import duongmh3.launcher.common.event.EventFireUtil;
import duongmh3.launcher.common.event.OnActionData;
import duongmh3.launcher.common.list.RecyclerButterKnifeViewHolder;
import duongmh3.launcher.common.list.ViewHolderBinder;
import duongmh3.launcher.common.task.TaskManager;

/**
 * Created by admin on 7/15/17.
 */

public class AppViewModelBinder extends ViewHolderBinder<AppInfoViewModel, AppViewModelBinder.ViewHolder> {
    private OnActionData<View> onFocusEvent;
    private OnActionData<View> onLostFocusEvent;

    public AppViewModelBinder(OnActionData<View> onFocusEvent, OnActionData<View> onLostFocusEvent) {
        this.onFocusEvent = onFocusEvent;
        this.onLostFocusEvent = onLostFocusEvent;
    }

    @NonNull
    @Override
    protected ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        ViewHolder viewHolder = ViewHolder.newInstance(inflater, parent);
        viewHolder.itemView.setFocusable(true);
        viewHolder.itemView.setTag(new TaskManager());
        return viewHolder;
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull AppInfoViewModel item) {
        holder.itemView.setOnFocusChangeListener((view, focused) -> {
            if (focused) {
                EventFireUtil.fireEvent(onFocusEvent, holder.itemView);
            } else {
                EventFireUtil.fireEvent(onLostFocusEvent, holder.itemView);
            }
        });

        TaskManager taskManager = (TaskManager) holder.itemView.getTag();
        taskManager.destroy();
        LoadAppIconUseCase loadAppIconUseCase = new LoadAppIconUseCase(taskManager);
        loadAppIconUseCase.executeAsync(item.getPackageName(), (OnActionData<DataResult<Drawable>>) data -> {
            if (data.isSuccess()) {
                holder.ivApp.setImageDrawable(data.getData());
            }
        });
        setText(holder.tvAppName, item.getName());
    }

    @Override
    protected void onViewDetachedFromWindow(@NonNull ViewHolder holder) {
        super.onViewDetachedFromWindow(holder);
        TaskManager taskManager = (TaskManager) holder.itemView.getTag();
        taskManager.destroy();
    }

    public static class ViewHolder extends RecyclerButterKnifeViewHolder {
        @BindView(R.id.ivApp)
        AppCompatImageView ivApp;
        @BindView(R.id.tvAppName)
        TextView tvAppName;

        public ViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent, @LayoutRes int layoutId) {
            super(inflater, parent, layoutId);
        }

        public static ViewHolder newInstance(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
            return new ViewHolder(inflater, parent, R.layout.app_view_holder);
        }
    }
}
