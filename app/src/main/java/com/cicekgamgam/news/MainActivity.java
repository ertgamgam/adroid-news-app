package com.cicekgamgam.news;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cicekgamgam.news.service.news.ArticleDto;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth.AuthStateListener mAuthStateListener;
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

    public void getNewsDetails(View view) {



        //startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse()));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.optionsmenu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.logoutbtn:
                Toast.makeText(this,"You logged out",Toast.LENGTH_SHORT).show();
                FirebaseAuth.getInstance().signOut();
                Intent goestoSignUp=new Intent(MainActivity.this,SignUpActivity.class);
                startActivity(goestoSignUp);

                break;
            case R.id.weatherbtn:
                Toast.makeText(this,"You clicked weather ",Toast.LENGTH_SHORT).show();
                break;
            case R.id.currencybtn:
                Toast.makeText(this,"You clicked currency",Toast.LENGTH_SHORT).show();
                break;
        }
        return true;
    }
}
