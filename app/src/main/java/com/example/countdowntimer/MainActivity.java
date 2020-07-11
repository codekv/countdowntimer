package com.example.countdowntimer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import ticker.views.com.ticker.widgets.circular.timer.callbacks.CircularViewCallback;
import ticker.views.com.ticker.widgets.circular.timer.view.CircularView;

public class MainActivity extends AppCompatActivity {

    CircularView circularViewWithTimer;
    EditText times;
    long time;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        View decorView = getWindow().getDecorView();
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.KITKAT){
            decorView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
            );
        }
    }

    public  void go(View view)
    {
        times = (EditText)findViewById(R.id.Time);

        if(TextUtils.isEmpty(times.getText())){
            Toast.makeText(this, "Enter Your Time Limit", Toast.LENGTH_SHORT).show();
        }else {
            time=Long.parseLong(times.getText().toString());
            circularViewWithTimer = findViewById(R.id.circular_view);
            CircularView.OptionsBuilder builderWithTimer =
                    new CircularView.OptionsBuilder()
                            .shouldDisplayText(true)
                            .setCounterInSeconds(time)
                            .setCircularViewCallback(new CircularViewCallback() {
                                @Override
                                public void onTimerFinish() {

                                    // Will be called if times up of countdown timer
                                    Toast.makeText(MainActivity.this, "CircularCallback: Timer Finished ", Toast.LENGTH_SHORT).show();
                                }

                                @Override
                                public void onTimerCancelled() {

                                    // Will be called if stopTimer is called
                                    Toast.makeText(MainActivity.this, "CircularCallback: Timer Stoped ", Toast.LENGTH_SHORT).show();
                                }
                            });

            circularViewWithTimer.setOptions(builderWithTimer);
        }
    }
    public void btn_start(View view)
    {
        circularViewWithTimer.startTimer();
    }

    public void btn_stop(View view)
    {
        circularViewWithTimer.stopTimer();
    }


}