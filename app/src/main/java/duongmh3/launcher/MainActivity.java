package duongmh3.launcher;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.zhy.android.percent.support.PercentRelativeLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import duongmh3.launcher.common.Util;
import duongmh3.launcher.common.list.RecyclerItems;
import duongmh3.launcher.common.task.TaskManager;
import duongmh3.launcher.func.listapp.AppInfoViewModel;
import duongmh3.launcher.func.listapp.LoadAppUseCase;
import me.drakeet.multitype.MultiTypeAdapter;

public class MainActivity extends AppCompatActivity {
    @NonNull
    private final TaskManager taskManager = new TaskManager();
    @BindView(R.id.vgHeader)
    RelativeLayout vgHeader;
    @BindView(R.id.cv1)
    CardView cv1;
    @BindView(R.id.cv2)
    CardView cv2;
    @BindView(R.id.cv3)
    CardView cv3;
    @BindView(R.id.cv4)
    CardView cv4;
    @BindView(R.id.cv5)
    CardView cv5;
    @BindView(R.id.cv6)
    CardView cv6;
    @BindView(R.id.cv7)
    CardView cv7;
    @BindView(R.id.cv8)
    CardView cv8;
    @BindView(R.id.slider)
    SliderLayout slider;
    @BindView(R.id.vgFixApp)
    PercentRelativeLayout vgFixApp;
    @BindView(R.id.tvApps)
    TextView tvApps;
    @BindView(R.id.cvApp1)
    CardView cvApp1;
    @BindView(R.id.cvApp2)
    CardView cvApp2;
    @BindView(R.id.cvApp3)
    CardView cvApp3;
    @BindView(R.id.cvApp4)
    CardView cvApp4;
    @BindView(R.id.cvApp5)
    CardView cvApp5;
    @BindView(R.id.cvApp6)
    CardView cvApp6;
    @BindView(R.id.cvApp7)
    CardView cvApp7;
    @BindView(R.id.cvApp8)
    CardView cvApp8;
    @BindView(R.id.cvApp9)
    CardView cvApp9;
    @BindView(R.id.cvApp10)
    CardView cvApp10;


    //private TVGridView rvApp;
    private RecyclerItems<AppInfoViewModel> items = new RecyclerItems<>();
    @NonNull
    private final LoadAppUseCase loadAppUseCase = new LoadAppUseCase(taskManager);
    @NonNull
    private final MultiTypeAdapter multiTypeAdapter = new MultiTypeAdapter();

    private SliderLayout sliderLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    private void initView() {
        //rvApp = (TVGridView) findViewById(R.id.rvApp);
        //rvApp.setStrokeColorClicked(Color.RED);
        //rvApp.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        //rvApp.setHasFixedSize(true);
        //multiTypeAdapter.register(AppInfoViewModel.class, new AppViewModelBinder(this::onFocus, this::onLostFocus));
        //rvApp.setAdapter(multiTypeAdapter);
        //multiTypeAdapter.setItems(items);
        //multiTypeAdapter.notifyDataSetChanged();

        sliderLayout = (SliderLayout) findViewById(R.id.slider);
    }

    private void initData() {
        loadAppUseCase.executeAsync(data -> {
            if (data.isSuccess()) {
                items.clear();
                items.addAll(data.getData());
                //multiTypeAdapter.notifyDataSetChanged();
            }
        });

        {
            TextSliderView textSliderView = new TextSliderView(this);
            // initialize a SliderLayout
            textSliderView
                    .description("Alibay.vn uy tín chất lượng")
                    .image(R.drawable.slider_1)
                    .setScaleType(BaseSliderView.ScaleType.Fit);
            sliderLayout.addSlider(textSliderView);
        }
        {
            TextSliderView textSliderView = new TextSliderView(this);
            // initialize a SliderLayout
            textSliderView
                    .description("Alibay.vn vận chuyển toàn quốc")
                    .image(R.drawable.slider_2)
                    .setScaleType(BaseSliderView.ScaleType.Fit);
            sliderLayout.addSlider(textSliderView);
        }
        sliderLayout.setPresetTransformer(SliderLayout.Transformer.Accordion);
        sliderLayout.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        sliderLayout.setDuration(8000);
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN)
            if (event.getKeyCode() == KeyEvent.KEYCODE_DPAD_CENTER) {
                Log.d("dispatchKeyEvent", "KeyEvent.KEYCODE_DPAD_CENTER");
                return true;
            }
        return false;
    }

    @Override
    protected void onStop() {
        super.onStop();
        sliderLayout.stopAutoCycle();
    }

    private void onLostFocus(View view) {
        //rvApp.selectView(view, false);
    }

    private void onFocus(View view) {
        //rvApp.selectView(view, true);
    }

    @OnClick({R.id.cv1, R.id.cv2, R.id.cv3, R.id.cv4, R.id.cv5, R.id.cv6, R.id.cv7, R.id.cv8})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.cv1:
                Util.openApp(this, "com.google.android.youtube");
                break;
            case R.id.cv2:
                Util.openApp(this, "vng.zing.mp3");
                break;
            case R.id.cv3:
                Util.openApp(this, "com.google.android.apps.photos");
                break;
            case R.id.cv4:
                Util.openApp(this, "com.android.vending");
                break;
            case R.id.cv5:
                Util.openApp(this, "com.android.chrome");
                break;
            case R.id.cv6:
                Util.openApp(this, "com.google.android.apps.maps");
                break;
            case R.id.cv7:
                Util.openSetting(this);
                break;
            case R.id.cv8:
                break;
        }
    }
}
