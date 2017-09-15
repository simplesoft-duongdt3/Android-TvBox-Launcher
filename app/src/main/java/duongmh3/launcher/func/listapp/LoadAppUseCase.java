package duongmh3.launcher.func.listapp;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.support.annotation.NonNull;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import duongmh3.launcher.App;
import duongmh3.launcher.common.AppException;
import duongmh3.launcher.common.task.ITaskManager;
import duongmh3.launcher.common.usecase.UseCaseOutput;

/**
 * Created by admin on 7/16/17.
 */

public class LoadAppUseCase extends UseCaseOutput<List<AppInfoViewModel>> {

    public LoadAppUseCase(@NonNull ITaskManager taskManager) {
        super(taskManager);
    }

    @NonNull
    @Override
    public List<AppInfoViewModel> executeInternal() throws AppException {
        long start = System.nanoTime();

        List<AppInfoViewModel> apps = new ArrayList<>();

        Context context = App.getApp();
        PackageManager manager = context.getPackageManager();

        Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
        mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);

        for (ResolveInfo info : manager.queryIntentActivities(mainIntent, 0)) {
            ApplicationInfo appInfo = info.activityInfo.applicationInfo;
            AppInfoViewModel app = new AppInfoViewModel();

            app.id = appInfo.packageName + "/" + info.activityInfo.name;
            app.setName(info.loadLabel(manager).toString());
            app.packageName  = appInfo.packageName;
            app.activityName = info.activityInfo.name;
            apps.add(app);
        }

        long end = System.nanoTime();
        Log.i("time", Long.toString((end - start) / 1000000) + " milliseconds to list apps");
        return apps;
    }
}
