package duongmh3.launcher.func.listapp;

import duongmh3.launcher.common.list.RecyclerViewModel;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by duongmatheo on 7/12/17.
 */

@Setter
@Getter
public class AppInfoViewModel implements RecyclerViewModel {
    public String id = "(none)";

    // Name for this pojo, e.g. app name
    public String name = "";

    // Lower-cased name, for faster search
    public String nameNormalized = "";
    // Name displayed on the screen, may contain HTML (for instance, to put
    // query text in blue)
    public String displayName = "";

    public String packageName;
    public String activityName;

    @Override
    public int getSpanSize() {
        return 1;
    }
}
