package duongmh3.launcher;

import android.app.Application;
import android.support.v7.app.AppCompatDelegate;

import io.paperdb.Paper;

/**
 * Created by admin on 7/7/17.
 */

public class App extends Application {
    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

    private static App app;

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        Paper.init(this);
    }

    public static App getApp() {
        return app;
    }
}
