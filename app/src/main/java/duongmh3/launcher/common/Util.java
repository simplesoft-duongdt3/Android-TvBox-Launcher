package duongmh3.launcher.common;

import android.content.Context;
import android.content.Intent;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

import android.net.Uri;
import android.os.AsyncTask;

/**
 * Created by duongmatheo on 7/12/17.
 */

public class Util {
    public static void checkAndCancelTasks(AsyncTask task) {
        if (task != null && task.getStatus() != AsyncTask.Status.FINISHED) {
            task.cancel(true);
        }
    }

    public static void showWebBrowserByMarketName(Context context, String marketName) {
        String url = "https://bittrex.com/Market/Index?MarketName=" + marketName;
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        context.startActivity(intent);
    }

    public static String formatNumber(double value, String format) {
        DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.ENGLISH);
        DecimalFormat numberFormat = new DecimalFormat(format, symbols);
        return numberFormat.format(value);
    }
}
