package com.cicekgamgam.news.service.news;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NewsApiRetrofitRequest {

    @GET("v2/top-headlines")
    Call<GetTopHeadlinesResponse> getTopHeadlinesNews(@Query("page") int page,
                                                      @Query("pageSize") int pageSize,
                                                      @Query("country") String country,
                                                      @Query("apiKey") String apiKey);

}
