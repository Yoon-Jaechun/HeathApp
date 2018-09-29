package com.jcyoon.heathapp;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class TimerActivity extends AppCompatActivity {
    private TextView timerValue;
    private long remainTime = 0L;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);

        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MAIN_ACTIVITY_TIME);

        timerValue = (TextView) findViewById(R.id.timeView);
        remainTime = Long.parseLong(message)*1000;

        final CountDownTimer timer = new CountDownTimer(remainTime, 1000) {

            public void onTick(long millisUntilFinished) {
                timerValue.setText(String.valueOf(millisUntilFinished / 1000));
            }

            public void onFinish() {
                timerValue.setText("없음");
            }
        }.start();

        Button restartButton = (Button) findViewById(R.id.restartBtn);
        restartButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                timer.start();
            }
        });

        Button stopButton = (Button) findViewById(R.id.stopBtn);
        stopButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                timer.cancel();
            }
        });

    }
}
