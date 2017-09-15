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

public abstract class UseCaseOutput<O> {
    @NonNull
    private final TaskProcessor<O> taskProcessor;

    public UseCaseOutput(@NonNull ITaskManager taskManager) {
        taskProcessor = new TaskProcessor<>(taskManager);
    }

    @DebugLog
    @NonNull
    public abstract O executeInternal() throws AppException;

    public final DataResult<O> execute() {
        return taskProcessor.execute(this::executeInternal);
    }

    public final void executeAsync(@Nullable OnActionData<DataResult<O>> onResultEvent) {
        taskProcessor.executeAsync(this::executeInternal, onResultEvent);
    }

    public final void executeAsyncSingle(@Nullable OnActionData<DataResult<O>> onResultEvent) {
        taskProcessor.executeAsyncSingle(this::executeInternal, onResultEvent);
    }

    public final void executeAsyncLoading(Activity activity, @Nullable OnActionData<DataResult<O>> onResultEvent) {
        taskProcessor.executeAsyncLoading(activity, this::executeInternal, onResultEvent);
    }
}
