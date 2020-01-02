package com.cicekgamgam.news.service.news;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NewsApiRetrofitRequest {

    @GET("v2/everything")
    Call<GetTopHeadlinesResponse> getTopHeadlinesNews(@Query("page") int page,
                                                      @Query("pageSize") int pageSize,
                                                      @Query("q") String searchKey,
                                                      @Query("apiKey") String apiKey);

}
