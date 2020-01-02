package com.cicekgamgam.news;

import android.os.Bundle;
import android.webkit.WebView;

import androidx.appcompat.app.AppCompatActivity;

public class WeatherActivity extends AppCompatActivity {

    private static final String WEATHER_URL = "https://www.bbc.com/weather/745044";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        WebView myWebView = findViewById(R.id.webview);
        myWebView.loadUrl(WEATHER_URL);
    }

}
