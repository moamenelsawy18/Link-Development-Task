package com.task.linkdev.details;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableField;
import androidx.databinding.ObservableList;

import com.task.linkdev.base.BaseViewModel;
import com.task.linkdev.data.Article;

public class DetailsViewModel extends BaseViewModel {

    public final ObservableField<Article> article = new ObservableField<>();

    public DetailsViewModel(@NonNull Application application) {
        super(application);
    }


    public void start(Article article) {
        this.article.set(article);
    }

}
