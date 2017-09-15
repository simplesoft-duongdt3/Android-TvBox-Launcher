package duongmh3.launcher.common.usecase;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import duongmh3.launcher.common.AppException;
import duongmh3.launcher.common.DataResult;
import duongmh3.launcher.common.event.OnActionData;
import duongmh3.launcher.common.task.ITaskManager;
import duongmh3.launcher.common.task.TaskProcessor;
import hugo.weaving.DebugLog;

/**
 * Created by duongmatheo on 6/8/17.
 */

public abstract class UseCaseInput<I> {
    @NonNull
    private final TaskProcessor<Void> taskProcessor;

    public UseCaseInput(@NonNull ITaskManager taskManager) {
        taskProcessor = new TaskProcessor<>(taskManager);
    }

    @DebugLog
    public abstract void executeInternal(@NonNull I input) throws AppException;

    public final DataResult<Void> execute(@NonNull I input) {
        return taskProcessor.execute(() -> {
            executeInternal(input);
            return null;
        });
    }

    public final void executeAsync(I input, @Nullable OnActionData<DataResult<Void>> onResultEvent) {
        taskProcessor.executeAsync(() -> {
            executeInternal(input);
            return null;
        }, onResultEvent);
    }

    public final void executeAsyncSingle(I input, @Nullable OnActionData<DataResult<Void>> onResultEvent) {
        taskProcessor.executeAsyncSingle(() -> {
            executeInternal(input);
            return null;
        }, onResultEvent);
    }

    public final void executeAsyncLoading(Activity activity, I input, @Nullable OnActionData<DataResult<Void>> onResultEvent) {
        taskProcessor.executeAsyncLoading(activity, () -> {
            executeInternal(input);
            return null;
        }, onResultEvent);
    }
}
