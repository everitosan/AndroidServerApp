package com.example.evesan.servertemp;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class Settings extends AppCompatActivity {

    private EditText portNumber = null;
    private String port = null;
    private Button setPortButton = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        portNumber = (EditText) findViewById(R.id.portNumber);
        port = "";
        setPortButton = (Button) findViewById(R.id.port_button);


        //enables and disables button for the port
        portNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                port = portNumber.getText().toString();


                if (!"".equals(port) && port.length() == 4) {
                    setPortButton.setEnabled(true);
                } else
                {
                    setPortButton.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    public void setPortAction(View view) {
        port = portNumber.getText().toString();

        MainActivity.appServer.setPort(port);
        MainActivity.appServer.startServer();
        finish();

    }
}
