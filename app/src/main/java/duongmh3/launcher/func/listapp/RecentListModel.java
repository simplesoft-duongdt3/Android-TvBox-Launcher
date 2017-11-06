package duongmh3.launcher.func.listapp;

import java.util.List;

import duongmh3.launcher.common.Util;
import lombok.Getter;

/**
 * Created by admin on 11/5/17.
 */

@Getter
public class RecentListModel {

    private AppInfoViewModel appInfoViewModel1;
    private AppInfoViewModel appInfoViewModel2;
    private AppInfoViewModel appInfoViewModel3;
    private AppInfoViewModel appInfoViewModel4;
    private AppInfoViewModel appInfoViewModel5;
    private AppInfoViewModel appInfoViewModel6;
    private AppInfoViewModel appInfoViewModel7;
    private AppInfoViewModel appInfoViewModel8;
    private AppInfoViewModel appInfoViewModel9;
    private AppInfoViewModel appInfoViewModel10;

    public RecentListModel(List<AppInfoViewModel> list) {
        appInfoViewModel1 = Util.getObFromList(0, list);
        appInfoViewModel2 = Util.getObFromList(1, list);
        appInfoViewModel3 = Util.getObFromList(2, list);
        appInfoViewModel4 = Util.getObFromList(3, list);
        appInfoViewModel5 = Util.getObFromList(4, list);
        appInfoViewModel6 = Util.getObFromList(5, list);
        appInfoViewModel7 = Util.getObFromList(6, list);
        appInfoViewModel8 = Util.getObFromList(7, list);
        appInfoViewModel9 = Util.getObFromList(8, list);
        appInfoViewModel10 = Util.getObFromList(9, list);
    }
}
