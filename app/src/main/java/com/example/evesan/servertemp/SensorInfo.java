package com.example.evesan.servertemp;

import android.widget.TextView;

/**
 * Created by evesan on 4/18/16.
 */
public class SensorInfo {

    private int index = 0;
    private String measure = null;
    private boolean uiprinted = false;
    private TextView textV = null;

    public SensorInfo(int index) {
        this.index = index;
    }

    public void setMeasure(String measure) {
        this.measure = measure;
    }
    public String getMeasure(){
        return this.measure;
    }
    public int getIndex() {
        return this.index;
    }
    public boolean isUiPrinted() {
        return this.uiprinted;
    }
    public void registedPrinted(boolean printed) {
         this.uiprinted = printed;
    }

    public void setTextV(TextView tv) {
        this.textV = tv;
    }

    public TextView getTextV() {
        return this.textV;
    }
}