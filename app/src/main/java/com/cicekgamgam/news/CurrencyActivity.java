package com.cicekgamgam.news;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;

public class CurrencyActivity extends AppCompatActivity {

    private static final String CURRENCY_URL = "https://m.economictimes.com/markets/forex/currency-converter";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currency);
        WebView myWebView = findViewById(R.id.webview);
        myWebView.loadUrl(CURRENCY_URL);
    }
}
