package com.task.linkdev.main;

import android.app.Application;

import androidx.annotation.NonNull;

import com.task.linkdev.base.BaseViewModel;
import com.task.linkdev.data.ApiResponse;
import com.task.linkdev.data.ArticlesRepository;
import com.task.linkdev.network.NetworkState;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ArticlesViewModel extends BaseViewModel {

    private ArticlesRepository articlesRepository;
    public NetworkState networkState;

    public ArticlesViewModel(@NonNull Application application) {
        super(application);

        articlesRepository = new ArticlesRepository(application);
    }

    public void start(){
        getNextWebArticles();
    }

    private void getNextWebArticles() {
        networkState = NetworkState.RUNNING;

        mCompositeDisposable.add(articlesRepository.getNextWebArticles()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        this::onGetArticlesLoaded,
                        this::onGetArticlesFail
                ));
    }

    private void onGetArticlesLoaded(ApiResponse response){
        articles.addAll(response.getArticles());
        getAssociatedPressArticles();
    }

    private void getAssociatedPressArticles() {
        mCompositeDisposable.add(articlesRepository.getAssociatedPressArticles()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        this::onGetAssociatedPressArticlesLoaded,
                        this::onGetArticlesFail
                ));
    }

    private void onGetAssociatedPressArticlesLoaded(ApiResponse response){
        networkState = NetworkState.SUCCESS;
        articles.addAll(response.getArticles());
    }

    private void onGetArticlesFail(Throwable throwable) {
        networkState = NetworkState.FAILED;
    }

}
