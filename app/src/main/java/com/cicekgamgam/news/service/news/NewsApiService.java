package com.cicekgamgam.news.service.news;

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

public class NewsApiService {

    private static NewsApiService newsApiService;
    private final static String COUNTRY = "tr";
    private final static String API_KEY = "572e4629f6254f5d88d03c6a78a6b69b";
    private final static int PAGE_SIZE = 5;

    public static NewsApiService getInstance() {

        if (newsApiService == null) {
            synchronized (NewsApiService.class) {
                if (newsApiService == null) {
                    newsApiService = new NewsApiService();
                }
            }
        }

        return newsApiService;
    }

    private NewsApiRetrofitRequest newsApiRetrofitRequest;

    private NewsApiService() {
        Retrofit retrofit = NewsApiRetrofitClient.getInstance();
        newsApiRetrofitRequest = retrofit.create(NewsApiRetrofitRequest.class);
    }


    public List<ArticleDto> getTopHeadlinesNews(int page) {

        Call<GetTopHeadlinesResponse> call =
                newsApiRetrofitRequest.getTopHeadlinesNews(page, PAGE_SIZE, COUNTRY, API_KEY);

        GetTopHeadlinesResponse responseBody = null;
        try {
            Response<GetTopHeadlinesResponse> response = call.execute();
            responseBody = response.body();
        } catch (Exception ex) {
            System.out.println("An exception occurred = "+ex.getMessage());
        }

        return responseBody.getArticles();
    }
}
