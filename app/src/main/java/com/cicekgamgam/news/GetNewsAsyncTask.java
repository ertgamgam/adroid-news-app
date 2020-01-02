package com.cicekgamgam.news;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.cicekgamgam.news.service.news.ArticleDto;
import com.cicekgamgam.news.service.news.NewsApiService;

import java.lang.ref.WeakReference;
import java.util.List;

public class GetNewsAsyncTask extends AsyncTask<Object, Void, List<ArticleDto>> {

    private WeakReference<MainActivity> mainActivityWeakReference;

    GetNewsAsyncTask(MainActivity mainActivity) {
        mainActivityWeakReference = new WeakReference<>(mainActivity);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        MainActivity mainActivity = mainActivityWeakReference.get();
        mainActivity.progressDialog.show();
    }

    @Override
    protected List<ArticleDto> doInBackground(Object... params) {
        NewsApiService newsApiService = NewsApiService.getInstance();
        return newsApiService.getNews((Integer) params[0], (String) params[1]);
    }

    @Override
    protected void onPostExecute(List<ArticleDto> articles) {
        super.onPostExecute(articles);
        MainActivity mainActivity = mainActivityWeakReference.get();
        if (!articles.isEmpty()) {
            NewsAdapter newsAdapter = mainActivity.newsAdapter;
            newsAdapter.addNews(articles);
        }

        if (mainActivity.progressDialog.isShowing()) {
            mainActivity.progressDialog.hide();
        }
    }
}
