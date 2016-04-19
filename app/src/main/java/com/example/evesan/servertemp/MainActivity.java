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
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    final static String TAG = "com.example.evesan.servertemp";

    static MyServer appServer;
    static TextView temperature;
    FloatingActionButton addSensor = null;
    static Handler messageHandler = new MessageHandler();
    static Context mainContext = null;
    static ArrayList<MyServer> ServerSockets = new ArrayList<MyServer>();


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

        temperature = (TextView) findViewById(R.id.receivedTemp);
        mainContext= MainActivity.this;

    }





    //Class for update the temperature reporter
    public static class MessageHandler extends Handler {
        @Override
        public void handleMessage(Message message) {
            String measure = (String)message.obj;
            temperature.setText(measure);
            Log.d("---", measure);
        }
    }

}
