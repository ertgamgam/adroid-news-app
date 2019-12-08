package com.cicekgamgam.news;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    TextView descriptionTextView;
    Button getNewsButton;
    ProgressDialog progressDialog;

    private int pageNumber = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        descriptionTextView = findViewById(R.id.descriptionTextView);
        getNewsButton = findViewById(R.id.getNewsButton);

        progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setMessage("Haberler geliyor...");
        progressDialog.setIndeterminate(false);
        progressDialog.setCancelable(false);
    }

    public void getNewsButtonOnClick(View view) {
        getNews();
    }


    private void getNews() {
        GetNewsAsyncTask getNewsAsyncTask = new GetNewsAsyncTask(this);
        getNewsAsyncTask.execute(++pageNumber);
    }
}
