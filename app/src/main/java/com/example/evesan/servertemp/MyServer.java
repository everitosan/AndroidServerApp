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

    private String port;
    private Handler messageHandler;
    private ServerIntentService service = null;
    private int index = 0;

    String TAG = MainActivity.TAG;

    public MyServer(Handler messageHandler) {
        this.messageHandler = messageHandler;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public void setIndex(int index) {
        this.index=  index;
    }

    public void startServer() {
        //Send the port and messenger
        this.service = new ServerIntentService(this.port, this.index, new Messenger(this.messageHandler));
        //start the server
        this.service.start();
    }


}
