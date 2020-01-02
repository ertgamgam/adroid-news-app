package com.cicekgamgam.news;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cicekgamgam.news.service.news.ArticleDto;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String DEFAULT_SEARCH_KEY = "new";
    ProgressDialog progressDialog;
    NewsAdapter newsAdapter;
    private String searchKey = DEFAULT_SEARCH_KEY;

    private int pageNumber = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView recyclerView = findViewById(R.id.newsRecyclerView);
        EditText searchEditText = findViewById(R.id.searchEditText);

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

        searchEditText.addTextChangedListener(new TextWatcher() {
            CountDownTimer timer = null;

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(final CharSequence s, int start, int before, int count) {
                if (timer != null) {
                    timer.cancel();
                }

                timer = new CountDownTimer(1300, 1000) {
                    public void onTick(long millisUntilFinished) {
                    }

                    public void onFinish() {
                        pageNumber = 1;
                        searchKey = TextUtils.isEmpty(s) ? DEFAULT_SEARCH_KEY : s.toString();
                        newsAdapter.clearAll();
                        getNews();
                    }

                }.start();

            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });


        getNews();
    }

    private void getNews() {
        if (pageNumber > 4) {
            pageNumber = 1;
        }
        GetNewsAsyncTask getNewsAsyncTask = new GetNewsAsyncTask(this);
        getNewsAsyncTask.execute(pageNumber++, searchKey);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.optionsmenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.logoutbtn:
                Toast.makeText(this, "You logged out", Toast.LENGTH_SHORT).show();
                FirebaseAuth.getInstance().signOut();
                Intent signInIntent = new Intent(MainActivity.this, SignInActivity.class);
                startActivity(signInIntent);

                break;
            case R.id.weatherbtn:
                Intent weatherIntent = new Intent(MainActivity.this, WeatherActivity.class);
                startActivity(weatherIntent);
                break;
            case R.id.currencybtn:
                Intent currencyActivity = new Intent(MainActivity.this, CurrencyActivity.class);
                startActivity(currencyActivity);
                break;
        }
        return true;
    }

    @Override
    public void onBackPressed() {
    }
}
