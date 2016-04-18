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

public class ServerIntentService  extends IntentService {

    ServerSocket myServerSocket;
    ServerResponse myResponse;
    String TAG = MainActivity.TAG;
    private Messenger messageHandler;

    public ServerIntentService() {
        super("ServerIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        //Obtain the port
        String port = intent.getStringExtra("port");
        //Obtain the messenger reference
        messageHandler = (Messenger) intent.getExtras().get("Messenger");

        try {
            myServerSocket = new ServerSocket(Integer.parseInt(port));
            Log.d(TAG,"Sever listening in port - " + port);
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
                    myResponse.run();
                    //Messenger response
                    Message message1 = new Message();
                    message1.obj = messageStr;
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

    @Override
    public void onDestroy() {
        Log.d(TAG,"Service ended");
        super.onDestroy();
    }
}
