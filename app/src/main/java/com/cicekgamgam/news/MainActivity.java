package com.cicekgamgam.news;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cicekgamgam.news.service.news.ArticleDto;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ProgressDialog progressDialog;
    NewsAdapter newsAdapter;

    private int pageNumber = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView recyclerView = findViewById(R.id.newsRecyclerView);

        newsAdapter = new NewsAdapter(this, new ArrayList<ArticleDto>());

        recyclerView.setAdapter(newsAdapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setMessage("Haberler geliyor...");
        progressDialog.setIndeterminate(false);
        progressDialog.setCancelable(false);

        recyclerView.addOnScrollListener(new LastItemScrollListener() {

            @Override
            public void onLastItemVisible() {
                getNews();
            }
        });

        getNews();
    }

    private void getNews() {
        if (pageNumber > 4) {
            pageNumber = 1;
        }
        GetNewsAsyncTask getNewsAsyncTask = new GetNewsAsyncTask(this);
        getNewsAsyncTask.execute(++pageNumber);
    }
}
