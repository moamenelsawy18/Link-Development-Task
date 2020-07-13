package com.task.linkdev.network;


import com.task.linkdev.data.ApiResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WebServices {

    @GET("articles")
    Observable<ApiResponse> getNextWebArticles(@Query("source") String source, @Query("apiKey") String apiKey);

    @GET("articles")
    Observable<ApiResponse> getAssociatedPressArticles(@Query("source") String source, @Query("apiKey") String apiKey);
}
