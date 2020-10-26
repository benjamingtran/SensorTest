package com.example.sensortest;

import android.app.Activity;

import android.hardware.SensorEvent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;


import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity  {

    private  Accelerometer accelerometer;
    private Gyroscope gyroscope;
    private TextView AXtext,AYtext,AZtext,GXtext,GYtext,GZtext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        accelerometer = new Accelerometer(this);
        gyroscope = new Gyroscope(this);
        AXtext = (TextView)findViewById(R.id.AXtext);
        AYtext = (TextView)findViewById(R.id.AYtext);
        AZtext = (TextView)findViewById(R.id.AZtext);
        GXtext = (TextView)findViewById(R.id.GXtext);
        GYtext = (TextView)findViewById(R.id.GYtext);
        GZtext = (TextView)findViewById(R.id.GZtext);


        accelerometer.setListener(new Accelerometer.Listener() {
            @Override
            public void onTranslation(float x, float y, float z) {
                AXtext.setText("X: " + x);
                AYtext.setText("Y: " + y);
                AZtext.setText("Z: " + z);

            }
        });
        gyroscope.setListener(new Gyroscope.Listener() {
            @Override
            public void onTranslation(float x, float y, float z) {
                GXtext.setText("X: " + x);
                GYtext.setText("Y: " + y);
                GZtext.setText("Z: " + z);
            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();
        accelerometer.register();
        gyroscope.register();
    }

    @Override
    protected void onPause() {
        super.onPause();
        accelerometer.unregister();
        gyroscope.unregister();
    }
}