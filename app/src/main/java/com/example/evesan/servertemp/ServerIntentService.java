package com.example.evesan.servertemp;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import java.io.DataInput;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by evesan on 4/15/16.
 */

public class ServerIntentService  extends IntentService {

    ServerSocket myServerSocket;
    String TAG = MainActivity.TAG;

    public ServerIntentService() {
        super("ServerIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        //what service does
        String port = intent.getStringExtra("port");
        Log.d(TAG,"wiwi" + port);

        try {
            myServerSocket = new ServerSocket(Integer.parseInt(port));
            while (true) {
                Socket socketClient = myServerSocket.accept();
                InputStream message = socketClient.getInputStream();


                byte [] messageByte = new byte[5];
                boolean end = false;
                String messageStr = "";
                int bytesRead;

                try{
                    DataInputStream messageInputStream = new DataInputStream(message);
                    while(!end) {
                        bytesRead = messageInputStream.read(messageByte);
                        messageStr += new String(messageByte, 0, bytesRead);
                        if (messageStr.length() == 5) {
                            end = true;
                        }
                    }
                    Log.d(TAG, messageStr);
                }catch(Exception e) {
                    e.printStackTrace();
                }

                Log.d("TAG","message from  - " + socketClient.getInetAddress().toString() );
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onDestroy() {
        Log.d(TAG,"Service ended");
        super.onDestroy();
    }
}
