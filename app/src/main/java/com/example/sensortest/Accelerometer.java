package com.example.sensortest;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

public class Accelerometer {

    public interface Listener
    {
        void onTranslation(float x , float y, float z);
    }
    private  Listener listener;
    public  void setListener(Listener l)
    {
        listener = l;
    }
    private SensorManager sm;
    private Sensor sensor;
    private SensorEventListener sensorEventListener;

    Accelerometer(Context context)
    {
        //Creating SensorManger
        sm = (SensorManager)context.getSystemService(Context.SENSOR_SERVICE);
        //Accelerator Sensor
        sensor = sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorEventListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                if(listener !=null)
                {
                    listener.onTranslation(event.values[0],event.values[1],event.values[2]);
                }
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        };
    }
    public void register()
    {
        sm.registerListener(sensorEventListener,sensor,SensorManager.SENSOR_DELAY_NORMAL);
    }
    public void unregister()
    {
        sm.unregisterListener(sensorEventListener);
    }
}
