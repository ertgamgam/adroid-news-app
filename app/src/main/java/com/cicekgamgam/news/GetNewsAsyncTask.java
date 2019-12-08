package com.cicekgamgam.news;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.cicekgamgam.news.service.news.ArticleDto;
import com.cicekgamgam.news.service.news.NewsApiService;

import java.lang.ref.WeakReference;
import java.util.List;

public class GetNewsAsyncTask extends AsyncTask<Integer, Void, List<ArticleDto>> {

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
    protected List<ArticleDto> doInBackground(Integer... page) {
        NewsApiService newsApiService = NewsApiService.getInstance();
        return newsApiService.getTopHeadlinesNews(page[0]);
    }

    @Override
    protected void onPostExecute(List<ArticleDto> articles) {

        super.onPostExecute(articles);
        MainActivity mainActivity = mainActivityWeakReference.get();
        if (articles.isEmpty()) {
            mainActivity.getNewsButton.setEnabled(false);
            Context context = mainActivity.getApplicationContext();
            CharSequence text = "Tüm haberler gösterildi!";
            Toast toast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
            toast.show();
        } else {
            //Add new items to scrollview
            mainActivity.descriptionTextView.setText(articles.get(0).getDescription());
            //
        }

        if (mainActivity.progressDialog.isShowing()) {
            mainActivity.progressDialog.hide();
        }
    }
}
