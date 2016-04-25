package com.example.evesan.servertemp;

import android.app.IntentService;
import android.content.Intent;
import android.os.Message;
import android.os.Messenger;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import java.io.DataInput;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by evesan on 4/15/16.
 */

public class ServerIntentService  extends Thread {

    private ServerSocket myServerSocket;
    private ServerResponse myResponse;
    private String TAG = MainActivity.TAG;
    private Messenger messageHandler;

    private String port = null;
    private SensorInfo sensorI = null;


    public ServerIntentService(String port, int index, Messenger myMessenger) {
        super("ServerIntentService");
        this.port = port;
        this.messageHandler = myMessenger;
        this.sensorI = new SensorInfo(index);
    }

    public void run() {

        try {
            myServerSocket = new ServerSocket(Integer.parseInt(port));
            Log.d(TAG,"Server listening in port - " + port);
            while (true) {
                Socket socketClient = myServerSocket.accept();
                InputStream message = socketClient.getInputStream();

                int messageLen = 5;
                byte [] messageByte = new byte[messageLen];
                boolean end = false;
                String messageStr = "";
                int bytesRead;

                try{
                    DataInputStream messageInputStream = new DataInputStream(message);
                    while(!end) {
                        bytesRead = messageInputStream.read(messageByte);
                        messageStr += new String(messageByte, 0, bytesRead);
                        if (messageStr.length() == messageLen) {
                            end = true;
                        }
                    }


                    //Response thread
                    myResponse = new ServerResponse(socketClient);
                    myResponse.start();
                    //Messenger response
                    this.sensorI.setMeasure(messageStr);
                    Message message1 = new Message();
                    message1.obj = this.sensorI;
                    messageHandler.send(message1);

                    Log.d(TAG, "Measures temperature - " + messageStr);
                }catch(Exception e) {
                    e.printStackTrace();
                }

                Log.d("TAG","message from  - " + socketClient.getInetAddress().toString() );
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
