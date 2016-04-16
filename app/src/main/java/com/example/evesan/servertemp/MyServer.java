package com.example.evesan.servertemp;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import java.net.ServerSocket;

/**
 * Created by evesan on 4/14/16.
 */
public class MyServer {
    Intent serviceIntent;
    Context main;
    String port;

    String TAG = MainActivity.TAG;

    public MyServer(Context c1, Class c2) {
        this.main = c1;
        this.serviceIntent = new Intent(c1, c2);
    }

    public void setPort(String port) {
        this.port = port;
    }

    public void startServer() {
        serviceIntent.putExtra("port", this.port);
        this.main.startService(serviceIntent);
    }
}
