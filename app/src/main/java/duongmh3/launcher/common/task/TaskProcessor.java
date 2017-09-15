package duongmh3.launcher.common.task;

import android.app.Activity;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import duongmh3.launcher.common.DataResult;
import duongmh3.launcher.common.Util;
import duongmh3.launcher.common.event.EventFireUtil;
import duongmh3.launcher.common.event.OnActionData;
import duongmh3.launcher.common.event.OnWorkingWithException;

/**
 * Created by duongmatheo on 6/9/17.
 */

public class TaskProcessor<O> {
    @NonNull
    private final ITaskManager taskManager;
    private AsyncTask task;

    public TaskProcessor(@NonNull ITaskManager taskManager) {
        this.taskManager = taskManager;
    }

    public final DataResult<O> execute(@NonNull OnWorkingWithException<O> onWorkingEvent) {
        return DataManagerUtil.request(() -> EventFireUtil.fireEvent(onWorkingEvent));
    }

    public final void executeAsync(@NonNull OnWorkingWithException<O> onWorkingEvent, @Nullable OnActionData<DataResult<O>> onResultEvent) {
        checkAndCancelTaskIfNeed();
        task = DataManagerUtil.requestAsync(taskManager, () -> EventFireUtil.fireEvent(onWorkingEvent), onResultEvent);
    }

    public final void executeAsyncLoading(Activity activity, @NonNull OnWorkingWithException<O> onWorkingEvent, @Nullable OnActionData<DataResult<O>> onResultEvent) {
        checkAndCancelTaskIfNeed();
        task = DataManagerUtil.requestAsyncLoading(activity, taskManager, () -> EventFireUtil.fireEvent(onWorkingEvent), onResultEvent);
    }

    public final void executeAsyncSingle(@NonNull OnWorkingWithException<O> onWorkingEvent, @Nullable OnActionData<DataResult<O>> onResultEvent) {
        checkAndCancelTaskIfNeed();
        task = DataManagerUtil.requestAsync(taskManager, () -> EventFireUtil.fireEvent(onWorkingEvent), onResultEvent, false);
    }

    private void checkAndCancelTaskIfNeed() {
        Util.checkAndCancelTasks(task);
    }
}
