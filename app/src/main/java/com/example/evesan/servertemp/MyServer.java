package com.example.evesan.servertemp;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import java.net.ServerSocket;
import android.os.Handler;
import android.os.Messenger;

/**
 * Created by evesan on 4/14/16.
 */
public class MyServer {
    private Intent serviceIntent;
    private Context main;
    private String port;
    private Handler messageHandler;

    String TAG = MainActivity.TAG;

    public MyServer(Context c1, Class c2, Handler messageHandler) {
        this.main = c1;
        this.serviceIntent = new Intent(c1, c2);
        this.messageHandler = messageHandler;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public void startServer() {
        //Send the port
        serviceIntent.putExtra("port", this.port);
        //Send the messenger handler reference
        serviceIntent.putExtra("Messenger", new Messenger(this.messageHandler));
        //start the server
        this.main.startService(serviceIntent);
    }
}
