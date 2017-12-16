package com.example.naman.fun_trip;

import android.app.Application;

/**
 * Created by naman on 14-Dec-17.
 */

public class GlobalVariables extends Application {
    private String phoneNumber;

    public String getData(){
        return phoneNumber;
    }

    public void setData(String phNumber){
        phoneNumber = phNumber;
    }
}
