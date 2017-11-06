package duongmh3.launcher.func.listapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.CardView;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import duongmh3.launcher.R;
import duongmh3.launcher.common.task.TaskManager;

/**
 * Created by admin on 11/5/17.
 */

public class ListAppRecentView {
    @BindView(R.id.ivApp1)
    AppCompatImageView ivApp1;
    @BindView(R.id.tvApp1)
    TextView tvApp1;
    @BindView(R.id.cvApp1)
    CardView cvApp1;
    @BindView(R.id.ivApp2)
    AppCompatImageView ivApp2;
    @BindView(R.id.tvApp2)
    TextView tvApp2;
    @BindView(R.id.cvApp2)
    CardView cvApp2;
    @BindView(R.id.ivApp3)
    AppCompatImageView ivApp3;
    @BindView(R.id.tvApp3)
    TextView tvApp3;
    @BindView(R.id.cvApp3)
    CardView cvApp3;
    @BindView(R.id.ivApp4)
    AppCompatImageView ivApp4;
    @BindView(R.id.tvApp4)
    TextView tvApp4;
    @BindView(R.id.cvApp4)
    CardView cvApp4;
    @BindView(R.id.ivApp5)
    AppCompatImageView ivApp5;
    @BindView(R.id.tvApp5)
    TextView tvApp5;
    @BindView(R.id.cvApp5)
    CardView cvApp5;
    @BindView(R.id.ivApp6)
    AppCompatImageView ivApp6;
    @BindView(R.id.tvApp6)
    TextView tvApp6;
    @BindView(R.id.cvApp6)
    CardView cvApp6;
    @BindView(R.id.ivApp7)
    AppCompatImageView ivApp7;
    @BindView(R.id.tvApp7)
    TextView tvApp7;
    @BindView(R.id.cvApp7)
    CardView cvApp7;
    @BindView(R.id.ivApp8)
    AppCompatImageView ivApp8;
    @BindView(R.id.tvApp8)
    TextView tvApp8;
    @BindView(R.id.cvApp8)
    CardView cvApp8;
    @BindView(R.id.ivApp9)
    AppCompatImageView ivApp9;
    @BindView(R.id.tvApp9)
    TextView tvApp9;
    @BindView(R.id.cvApp9)
    CardView cvApp9;
    @BindView(R.id.ivApp10)
    AppCompatImageView ivApp10;
    @BindView(R.id.tvApp10)
    TextView tvApp10;
    @BindView(R.id.cvApp10)
    CardView cvApp10;

    @NonNull
    private final LoadAppUseCase loadAppUseCase = new LoadAppUseCase(new TaskManager());

    public ListAppRecentView(Context context, ViewGroup viewGroup) {
        ButterKnife.bind(this, viewGroup);
    }

    public void loadData() {
        loadAppUseCase.executeAsync(data -> {
            if (data.isSuccess()) {
                List<AppInfoViewModel> list = data.getData();
                RecentListModel recentListModel = new RecentListModel(list);
                renderListRecent(recentListModel);
            }
        });
    }

    private void renderListRecent(RecentListModel recentListModel) {
        renderApp(ivApp1, tvApp1, cvApp1, recentListModel.getAppInfoViewModel1());
        renderApp(ivApp2, tvApp2, cvApp2, recentListModel.getAppInfoViewModel2());
        renderApp(ivApp3, tvApp3, cvApp3, recentListModel.getAppInfoViewModel3());
        renderApp(ivApp4, tvApp4, cvApp4, recentListModel.getAppInfoViewModel4());
        renderApp(ivApp5, tvApp5, cvApp5, recentListModel.getAppInfoViewModel5());
        renderApp(ivApp6, tvApp6, cvApp6, recentListModel.getAppInfoViewModel6());
        renderApp(ivApp7, tvApp7, cvApp7, recentListModel.getAppInfoViewModel7());
        renderApp(ivApp8, tvApp8, cvApp8, recentListModel.getAppInfoViewModel8());
        renderApp(ivApp9, tvApp9, cvApp9, recentListModel.getAppInfoViewModel9());
        renderApp(ivApp10, tvApp10, cvApp10, recentListModel.getAppInfoViewModel10());
    }

    private void renderApp(AppCompatImageView ivApp, TextView tvApp, CardView cvApp, AppInfoViewModel appInfoViewModel) {
        if (appInfoViewModel != null) {
            ivApp.setImageBitmap(null);
            tvApp.setText(appInfoViewModel.getDisplayName());
        } else {
            ivApp.setImageBitmap(null);
            tvApp.setText("");
        }
    }

}
