package com.cicekgamgam.news.service.news;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

class NewsApiRetrofitClient {

    private static Retrofit retrofit;
    private final static String NewsApiBaseUrl = "https://newsapi.org/";

    static Retrofit getInstance() {

        if (retrofit == null) {

            synchronized (NewsApiRetrofitClient.class) {

                if (retrofit == null) {

                    retrofit = new Retrofit.Builder()
                            .baseUrl(NewsApiBaseUrl)
                            .addConverterFactory(GsonConverterFactory.create())
                            .client(new OkHttpClient())
                            .build();

                }
            }
        }

        return retrofit;
    }

}
