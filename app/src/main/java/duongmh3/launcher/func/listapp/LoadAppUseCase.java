package duongmh3.launcher.func.listapp;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.LauncherActivityInfo;
import android.content.pm.LauncherApps;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Build;
import android.os.UserManager;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
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
        return loadApp(App.getApp());
    }

    protected List<AppInfoViewModel> loadApp(Context context) {
        long start = System.nanoTime();

        ArrayList<AppInfoViewModel> apps = new ArrayList<>();
        String excludedAppList = PreferenceManager.getDefaultSharedPreferences(context).
                getString("excluded-apps-list", context.getPackageName() + ";");
        List excludedApps = Arrays.asList(excludedAppList.split(";"));

        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            UserManager manager  = (UserManager)  context.getSystemService(Context.USER_SERVICE);
            LauncherApps launcher = (LauncherApps) context.getSystemService(Context.LAUNCHER_APPS_SERVICE);

            // Handle multi-profile support introduced in Android 5 (#542)
            for (android.os.UserHandle profile : manager.getUserProfiles()) {
                UserHandle user = new UserHandle(manager.getSerialNumberForUser(profile), profile);
                for (LauncherActivityInfo activityInfo : launcher.getActivityList(null, profile)) {
                    ApplicationInfo appInfo = activityInfo.getApplicationInfo();

                    String fullPackageName = user.addUserSuffixToString(appInfo.packageName, '#');
                    if(!excludedApps.contains(fullPackageName)) {
                        String label = activityInfo.getLabel().toString();
                        AppInfoViewModel app = new AppInfoViewModel();
                        app.id = appInfo.packageName + "/" + activityInfo.getName();
                        app.packageName  = appInfo.packageName;
                        app.displayName  = label;
                        app.activityName = activityInfo.getName();
                        apps.add(app);
                    }
                }
            }
        } else {
            PackageManager manager = context.getPackageManager();

            Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
            mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);

            for (ResolveInfo info : manager.queryIntentActivities(mainIntent, 0)) {
                ApplicationInfo appInfo = info.activityInfo.applicationInfo;
                if (!excludedApps.contains(appInfo.packageName)) {
                    String label = info.loadLabel(manager).toString();
                    AppInfoViewModel app = new AppInfoViewModel();

                    app.id = appInfo.packageName + "/" + info.activityInfo.name;
                    app.displayName = label;
                    app.packageName  = appInfo.packageName;
                    app.activityName = info.activityInfo.name;
                    apps.add(app);
                }
            }
        }

        Collections.sort(apps, (a1, a2) -> a1.getDisplayName().compareTo(a2.getDisplayName()));

        long end = System.nanoTime();
        Log.i("time", Long.toString((end - start) / 1000000) + " milliseconds to list apps");
        return apps;
    }
}
