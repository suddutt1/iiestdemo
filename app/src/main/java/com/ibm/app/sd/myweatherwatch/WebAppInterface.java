package com.ibm.app.sd.myweatherwatch;

import android.content.Context;
import android.provider.Settings;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

/**
 * Created by SUDDUTT1 on 1/3/2016.
 */
public class WebAppInterface {
    private String deviceId;
    Context mContext;

    /** Instantiate the interface and set the context */
    WebAppInterface(Context c,String deviceId) {
        mContext = c;
        this.deviceId = deviceId;
    }

    /** Show a toast from the web page */
    @JavascriptInterface
    public String getDeviceId() {
        //Toast.makeText(mContext, toast, Toast.LENGTH_SHORT).show();


        return this.deviceId;
    }
    @JavascriptInterface
    public void showAlert(String text) {
        Toast.makeText(mContext, text, Toast.LENGTH_SHORT).show();



    }
}
