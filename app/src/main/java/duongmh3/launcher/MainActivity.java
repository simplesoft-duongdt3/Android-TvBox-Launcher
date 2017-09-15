package duongmh3.launcher;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;

import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;

import java.util.HashMap;

import duongmh3.launcher.common.list.RecyclerItems;
import duongmh3.launcher.common.task.TaskManager;
import duongmh3.launcher.func.listapp.AppInfoViewModel;
import duongmh3.launcher.func.listapp.AppViewModelBinder;
import duongmh3.launcher.func.listapp.LoadAppUseCase;
import info.awesomedevelopment.tvgrid.library.TVGridView;
import me.drakeet.multitype.MultiTypeAdapter;

public class MainActivity extends AppCompatActivity {
    @NonNull
    private final TaskManager taskManager = new TaskManager();
    private TVGridView rvApp;
    private RecyclerItems<AppInfoViewModel> items = new RecyclerItems<>();
    @NonNull
    private final LoadAppUseCase loadAppUseCase = new LoadAppUseCase(taskManager);
    @NonNull
    private final MultiTypeAdapter multiTypeAdapter = new MultiTypeAdapter();;
    private SliderLayout sliderLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }

    private void initView() {
        rvApp = (TVGridView) findViewById(R.id.rvApp);
        rvApp.setStrokeColorClicked(Color.RED);
        rvApp.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        rvApp.setHasFixedSize(true);
        multiTypeAdapter.register(AppInfoViewModel.class, new AppViewModelBinder(this::onFocus, this::onLostFocus));
        rvApp.setAdapter(multiTypeAdapter);
        multiTypeAdapter.setItems(items);
        multiTypeAdapter.notifyDataSetChanged();

        sliderLayout = (SliderLayout) findViewById(R.id.slider);
    }

    private void initData() {
        loadAppUseCase.executeAsync(data -> {
            if (data.isSuccess()) {
                items.clear();
                items.addAll(data.getData());
                multiTypeAdapter.notifyDataSetChanged();
            }
        });

        HashMap<String,String> urlMaps = new HashMap<>();
        urlMaps.put("Hannibal", "http://static2.hypable.com/wp-content/uploads/2013/12/hannibal-season-2-release-date.jpg");
        urlMaps.put("Big Bang Theory", "http://tvfiles.alphacoders.com/100/hdclearart-10.png");
        urlMaps.put("House of Cards", "http://cdn3.nflximg.net/images/3093/2043093.jpg");
        urlMaps.put("Game of Thrones", "http://images.boomsbeat.com/data/images/full/19640/game-of-thrones-season-4-jpg.jpg");

        for(String name : urlMaps.keySet()){
            TextSliderView textSliderView = new TextSliderView(this);
            // initialize a SliderLayout
            textSliderView
                    .description(name)
                    .image(urlMaps.get(name))
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
        rvApp.selectView(view, false);
    }

    private void onFocus(View view) {
        rvApp.selectView(view, true);
    }
}
