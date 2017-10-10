package com.example.win81.acctest;

import android.content.pm.ActivityInfo;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener{

    SensorManager sm;
    Sensor sr;
    TextView txv;
    ImageView igv;
    RelativeLayout layout;
    double mx = 0,my = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);

        sm = (SensorManager) getSystemService(SENSOR_SERVICE);
        sr = sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        txv = (TextView)findViewById(R.id.textView);

        igv = (ImageView)findViewById(R.id.igvMove);
        layout = (RelativeLayout)findViewById(R.id.layout);

    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        if(mx ==0){
            mx = (layout.getWidth()-igv.getWidth())/20.0;
            my = (layout.getHeight()-igv.getHeight())/20.0;
        }

        RelativeLayout.LayoutParams parms = (RelativeLayout.LayoutParams) igv.getLayoutParams();
        parms.leftMargin = (int)((10-event.values[0])*mx);
        parms.topMargin = (int)((10+event.values[1])*my);

        igv.setLayoutParams(parms);

        txv.setText(String.format("X軸: %1.2f \n\n Y軸: %1.2f \n\nZ軸: %1.2f",event.values[0],event.values[1],event.values[2]));
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    @Override
    public void onResume(){
        super.onResume();
        sm.registerListener(this,sr,SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onPause(){
        super.onPause();
        sm.unregisterListener(this);
    }
}
