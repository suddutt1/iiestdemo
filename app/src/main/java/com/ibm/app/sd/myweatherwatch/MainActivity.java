package com.ibm.app.sd.myweatherwatch;

import android.provider.Settings;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.GeolocationPermissions;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {

    private static final int SPLASH_TIMEOUT = 4000;
    WebView myWebView;
    TextView textView;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String deviceId = Settings.Secure.getString(this.getContentResolver(),
                Settings.Secure.ANDROID_ID);

        //Set the Splash screen timing
        myWebView = (WebView)findViewById(R.id.webView);
        textView =(TextView)findViewById(R.id.textView);
        imageView = (ImageView)findViewById(R.id.logoView);

        myWebView.setWebViewClient(new WebViewClient()
        {
            public void onPageFinished(WebView view, String url) {

            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;

            }
        });

        myWebView.getSettings().setJavaScriptEnabled(true);
        myWebView.getSettings().setGeolocationEnabled(true);
        myWebView.getSettings().setAllowFileAccessFromFileURLs(true);
        myWebView.getSettings().setAllowUniversalAccessFromFileURLs(true);
        myWebView.setWebChromeClient(new WebChromeClient() {

            @Override
            public void onGeolocationPermissionsShowPrompt(String origin,
                                                           GeolocationPermissions.Callback callback) {
                // Always grant permission since the app itself requires location
                // permission and the user has therefore already granted it
                callback.invoke(origin, true, false);
            }

        });
        //Add java script interface
        myWebView.addJavascriptInterface(new WebAppInterface(this,deviceId), "Android");
        imageView.postDelayed(new Runnable() {
            @Override
            public void run() {
                imageView.setVisibility(View.GONE);
                textView.setVisibility(View.GONE);
                myWebView.setVisibility(View.VISIBLE);
            }
        },SPLASH_TIMEOUT);
       myWebView.loadUrl("file:///android_asset/www/index.html");
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        /*int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }*/

        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onBackPressed() {
        // Pop the browser back stack or exit the activity
        if (myWebView.canGoBack()) {
            myWebView.goBack();
        }
        else {
            super.onBackPressed();
        }
    }


}
