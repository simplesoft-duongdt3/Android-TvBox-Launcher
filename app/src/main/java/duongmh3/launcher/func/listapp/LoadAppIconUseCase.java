package duongmh3.launcher.func.listapp;

import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;

import duongmh3.launcher.App;
import duongmh3.launcher.common.AppException;
import duongmh3.launcher.common.task.ITaskManager;
import duongmh3.launcher.common.usecase.UseCase;

/**
 * Created by admin on 7/16/17.
 */

public class LoadAppIconUseCase extends UseCase<String, Drawable> {
    public LoadAppIconUseCase(@NonNull ITaskManager taskManager) {
        super(taskManager);
    }

    @NonNull
    @Override
    public Drawable executeInternal(@NonNull String packageName) throws AppException {
        Drawable icon;
        try {
            icon = App.getApp().getPackageManager().getApplicationIcon(packageName);
        } catch (Exception e) {
            throw new AppException(e);
        }
        return icon;
    }
}
