package com.cicekgamgam.news;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cicekgamgam.news.service.news.ArticleDto;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {

    private List<ArticleDto> articles;
    private LayoutInflater layoutInflater;

    NewsAdapter(Context context, List<ArticleDto> articles) {
        layoutInflater = LayoutInflater.from(context);
        this.articles = articles;
    }

    void addNews(List<ArticleDto> articles) {
        int currentNewsCount = getItemCount();
        this.articles.addAll(articles);
        notifyItemRangeChanged(currentNewsCount, articles.size());
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.news_card, parent, false);
        NewsViewHolder holder = new NewsViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
        ArticleDto article = articles.get(position);
        holder.setData(article, position);
    }

    @Override
    public int getItemCount() {
        return articles.size();
    }

    class NewsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView descriptionTextView;

        NewsViewHolder(@NonNull View view) {
            super(view);
            descriptionTextView = view.findViewById(R.id.news_card_description);
        }

        void setData(ArticleDto article, int position) {
            this.descriptionTextView.setText(article.getDescription());
        }

        @Override
        public void onClick(View v) {

        }
    }
}
