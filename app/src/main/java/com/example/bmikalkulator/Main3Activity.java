package com.example.bmikalkulator;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

import androidx.appcompat.app.AppCompatActivity;

public class Main3Activity extends AppCompatActivity {

    WebView myBrowser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        myBrowser = (WebView) findViewById(R.id.webview2);
        WebSettings settings = myBrowser.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setDomStorageEnabled(true);
        Intent i = getIntent();
        String url = i.getStringExtra("searchUrl");
        myBrowser.loadUrl(url);
    }

}
