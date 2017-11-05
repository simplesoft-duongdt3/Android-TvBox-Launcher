package duongmh3.launcher;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class EmptyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empty);
    }

    @Override
    protected void onResume() {
        super.onResume();

        Intent showOptions = new Intent(Intent.ACTION_MAIN);
        showOptions.addCategory(Intent.CATEGORY_HOME);
        startActivity(showOptions);
    }
}
