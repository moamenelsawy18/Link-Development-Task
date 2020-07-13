package com.task.linkdev.data;

import android.content.Context;


import com.task.linkdev.network.ApiClient;
import com.task.linkdev.network.WebServices;

import io.reactivex.Observable;

public class ArticlesRepository {

    private WebServices apiService;

    public ArticlesRepository(Context context) {
        apiService = ApiClient.getClient(context)
                .create(WebServices.class);
    }

    public Observable<ApiResponse> getNextWebArticles(){
        return apiService.getNextWebArticles("the-next-web", "533af958594143758318137469b41ba9");
    }

    public Observable<ApiResponse> getAssociatedPressArticles(){
        return apiService.getAssociatedPressArticles("associated-press", "533af958594143758318137469b41ba9");
    }
}
