package com.example.evesan.servertemp;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;

/**
 * Created by evesan on 4/16/16.
 */
public class ServerResponse extends Thread {

    Socket client;

    public ServerResponse(Socket clientSocket) {
        super("ServerResponse");
        this.client = clientSocket;
    }

    public void run() {
        OutputStream outStream;
        String replyMessage = "OK\n";

        try {
            outStream = this.client.getOutputStream();
            PrintStream pStream = new PrintStream(outStream);
            pStream.print(replyMessage);
            pStream.close();
        }
        catch (IOException e) {

        }
    }
}
