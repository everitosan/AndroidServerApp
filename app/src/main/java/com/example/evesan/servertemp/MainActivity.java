package com.example.evesan.servertemp;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    final static String TAG = "com.example.evesan.servertemp";


    FloatingActionButton addSensor = null;
    static Handler messageHandler = new MessageHandler();
    static Context mainContext = null;
    static ArrayList<MyServer> ServerSockets = new ArrayList<MyServer>();

    static LinearLayout LL = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        addSensor = (FloatingActionButton) findViewById(R.id.add_sensor);
        addSensor.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
                Intent settingsActivity = new Intent(MainActivity.this, Settings.class);
                startActivity(settingsActivity);
            }
         });


        LL = (LinearLayout) findViewById(R.id.container);
        mainContext= MainActivity.this;

    }





    //Class for update the temperature reporter
    public static class MessageHandler extends Handler {
        @Override
        public void handleMessage(Message message) {

            SensorInfo sensorI = (SensorInfo) message.obj;
            String tvs = "Sensor " + sensorI.getIndex() + " " +sensorI.getMeasure() + " ºC";

            if (!sensorI.isUiPrinted()) {
                TextView tv = new TextView(mainContext);

                tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 36);
                tv.setGravity(Gravity.CENTER);
                tv.setId(sensorI.getIndex());
                tv.setText(tvs);
                LL.addView(tv);
                sensorI.setTextV(tv);
                sensorI.registedPrinted(true);
            } else {
                TextView tv = sensorI.getTextV();
                tv.setText(tvs);
            }

        }
    }

}
