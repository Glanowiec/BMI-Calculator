package com.example.bmikalkulator;

import android.os.Bundle;
import android.webkit.WebView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Main2Activity extends AppCompatActivity {

    WebView myBrowser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        myBrowser = (WebView) findViewById(R.id.webview);
        myBrowser.loadUrl("file:///android_asset/authors.html");
    }
}
