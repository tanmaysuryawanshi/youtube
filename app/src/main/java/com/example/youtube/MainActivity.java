package com.example.youtube;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.PictureInPictureParams;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.util.Rational;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    WebView webView;
    Button button;
    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
Objects.requireNonNull(getSupportActionBar()).hide();

        webView=findViewById(R.id.webView);


        String url="https://www.youtube.com";

        WebView webView = new WebView(this);

        WebSettings webSettings = webView.getSettings();

        webSettings.setJavaScriptEnabled(true);
        webSettings.setPluginState(WebSettings.PluginState.ON);



        WebView.setWebContentsDebuggingEnabled(true);




        webView.setWebViewClient(new WebViewClient() {
            //

//


            @Override
            public void onPageFinished(WebView view, String url)
            {

                webView.loadUrl("javascript:(function() { " +
                        "document.getElementsByClassName('pivot-bar-item-tab pivot-shorts')[0].style.display='none'; })()");


            }
        });

        setContentView(webView);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(url);




        webView.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    WebView webView = (WebView) v;

                    switch(keyCode) {
                        case KeyEvent.KEYCODE_BACK:
                            if (webView.canGoBack()) {
                                webView.goBack();
                                return true;
                            }
                            break;
                    }
                }

                return false;
            }
        });

    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onUserLeaveHint () {
        Rational aspectRatio = new Rational(5, 4);
        PictureInPictureParams pictureInPictureParams=new PictureInPictureParams.Builder().setAspectRatio(aspectRatio).build();

            enterPictureInPictureMode(pictureInPictureParams);

    }




    @Override
    public void onPictureInPictureModeChanged(boolean isInPictureInPictureMode) {
        super.onPictureInPictureModeChanged(isInPictureInPictureMode);
        if(isInPictureInPictureMode){
            button.setVisibility(View.INVISIBLE);
        }
        else{
            button.setVisibility(View.VISIBLE);
        }
    }
}