package com.task.linkdev.base;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableField;
import androidx.databinding.ObservableList;
import androidx.lifecycle.AndroidViewModel;

import com.task.linkdev.data.Article;
import com.task.linkdev.utils.SingleLiveEvent;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import io.reactivex.disposables.CompositeDisposable;


public abstract class BaseViewModel extends AndroidViewModel {

    public CompositeDisposable mCompositeDisposable = new CompositeDisposable();
    public final ObservableList<Article> articles = new ObservableArrayList<>();
    private SingleLiveEvent<Article> mOpenDetails = new SingleLiveEvent<>();

    public BaseViewModel(@NonNull Application application) {
        super(application);
    }

    public SingleLiveEvent<Article> getOpenDetails(){
        return mOpenDetails;
    }

    public void openArticleDetails(Article item) {
        mOpenDetails.setValue(item);
    }

    public String getDateFormatted(String publishDate){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        Date date = null;

        try {
            date = (Date) formatter.parse(publishDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        SimpleDateFormat newFormat = new SimpleDateFormat("MMMM d, yyyy");
        return newFormat.format(date);
    }

}
