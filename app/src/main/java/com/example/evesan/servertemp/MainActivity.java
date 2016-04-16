package com.example.evesan.servertemp;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;



public class MainActivity extends AppCompatActivity {

    final static String TAG = "com.example.evesan.servertemp";

    static MyServer appServer;
    static TextView temperature;
    Handler messageHandler = new MessageHandler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        appServer = new MyServer(MainActivity.this,  ServerIntentService.class, messageHandler);
        temperature = (TextView) findViewById(R.id.receivedTemp);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_settings) {
            Intent settingsActivity = new Intent(MainActivity.this, Settings.class);
            startActivity(settingsActivity);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }



    //Class for update the temperature reporter
    public static class MessageHandler extends Handler {
        @Override
        public void handleMessage(Message message) {
            int measure = message.arg1;
            String report = String.valueOf(measure);
            temperature.setText(report);
            Log.d("---", report);
        }
    }

}
