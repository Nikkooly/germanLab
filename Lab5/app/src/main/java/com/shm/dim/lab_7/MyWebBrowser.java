package com.shm.dim.lab_7;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class MyWebBrowser extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.web);
        WebView webView = (WebView) findViewById(R.id.wv);
        Intent intent = getIntent();
        if (intent != null) {
            String link = intent.getStringExtra("LINK");
            if (link != null) {
                webView.loadUrl(link);
            } else {
                Toast.makeText(this, "Sorry, link not found", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Sorry, intent not found", Toast.LENGTH_SHORT).show();
        }
    }
}