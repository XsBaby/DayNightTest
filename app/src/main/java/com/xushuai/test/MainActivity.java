package com.xushuai.test;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

public class MainActivity extends Activity implements ThemeManager.OnThemeChangeListener {

    private Button day_night_mode;
    private RelativeLayout relativeLayout;
    private boolean isDay = true;//默认是日间模式

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ThemeManager.registerThemeChangeListener(this);
        day_night_mode = (Button) findViewById(R.id.day_night_mode);
        relativeLayout = (RelativeLayout) findViewById(R.id.relativeLayout);
        day_night_mode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isDay == true) {
                    ThemeManager.setThemeMode(ThemeManager.ThemeMode.NIGHT);
                    day_night_mode.setText("日间模式");
                    isDay = false;
                } else {
                    ThemeManager.setThemeMode(ThemeManager.ThemeMode.DAY);
                    day_night_mode.setText("夜间模式");
                    isDay = true;
                }
            }
        });
    }

    @Override
    public void onThemeChanged() {
        //日间模式下的颜色
        day_night_mode.setTextColor(getResources().getColor(ThemeManager.getCurrentThemeRes(MainActivity.this, R.color.textColor)));
        relativeLayout.setBackgroundColor(getResources().getColor(ThemeManager.getCurrentThemeRes(MainActivity.this, R.color.backgroundColor)));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ThemeManager.unregisterThemeChangeListener(this);
    }
}