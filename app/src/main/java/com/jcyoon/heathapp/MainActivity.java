package com.jcyoon.heathapp;

import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private Button startButton;
    private Button stopButton;
    private Button resetButton;
    private long startTime = 0L;
    private Handler customHandler = new Handler();
    long timeInMilliseconds = 0L;
    long updatedTime = 0L;
    long timeSwapBuff = 0L;
    private TextView timerValue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timerValue = (TextView) findViewById(R.id.timeView);

        startButton = (Button) findViewById(R.id.startBtn);
        startButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                startTime = SystemClock.uptimeMillis();
                customHandler.postDelayed(updateTimeThread,0);
            }
       });

        stopButton = (Button) findViewById(R.id.stopBtn);
        stopButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                timeSwapBuff += timeInMilliseconds;
                customHandler.removeCallbacks(updateTimeThread);
            }
        });

        resetButton = (Button) findViewById(R.id.resetBtn);
        resetButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                customHandler.removeCallbacks(updateTimeThread);
                startTime = 0L;
                timeSwapBuff = 0L;
                timeInMilliseconds = 0L;
                timerValue.setText("00:00:000");
            }
        });

    }

    private Runnable updateTimeThread = new Runnable(){
        public void run(){
            timeInMilliseconds = SystemClock.uptimeMillis() - startTime;
            updatedTime = timeSwapBuff + timeInMilliseconds;

            int secs = (int) (updatedTime / 1000);
            int min = secs / 60;
            int milliseconds = (int) (updatedTime % 1000);

            timerValue.setText("" + String.format("%02d", min) + ":"
                    + String.format("%02d",secs) + ":"
                    + String.format("%03d",milliseconds));
            customHandler.postDelayed(this, 0);
        }
    };
}
