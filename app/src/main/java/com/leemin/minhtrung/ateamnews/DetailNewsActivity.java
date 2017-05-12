package com.leemin.minhtrung.ateamnews;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class DetailNewsActivity extends AppCompatActivity {

    public static final String URLDETAIL = "url";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_news);

        WebView webView = (WebView) findViewById(R.id.webView );
        Intent intent = getIntent();
        String url = intent.getStringExtra(URLDETAIL);
        webView.loadUrl(url);
        webView.setWebViewClient(new WebViewClient());
    }
}
