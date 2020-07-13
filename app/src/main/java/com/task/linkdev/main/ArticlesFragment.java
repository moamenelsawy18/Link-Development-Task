package com.task.linkdev.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.task.linkdev.R;
import com.task.linkdev.data.Article;
import com.task.linkdev.databinding.ArticlesFragmentBinding;

import java.util.ArrayList;

public class ArticlesFragment extends Fragment {

    private ArticlesViewModel mViewModel;
    private ArticlesFragmentBinding mViewDataBinding;
    private ArticlesViewAdapter articlesViewAdapter;
    private Navigator navigator;

    public static ArticlesFragment newInstance() {
        return new ArticlesFragment();
    }

    @Override
    public void onStart() {
        super.onStart();
        mViewModel.start();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        navigator = (Navigator) getActivity();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        final View root = inflater.inflate(R.layout.articles_fragment, container, false);
        if (mViewDataBinding == null) {
            mViewDataBinding = ArticlesFragmentBinding.bind(root);
        }
        mViewModel = ViewModelProviders.of(this).get(ArticlesViewModel.class);

        mViewModel.getOpenDetails().observe(this , this::openDetails);

        mViewDataBinding.setViewModel(mViewModel);

        setUpRecycler();
        return mViewDataBinding.getRoot();
    }

    private void openDetails(Article article) {
        navigator.openDetails(article);
    }

    private void setUpRecycler() {
        RecyclerView recyclerView = mViewDataBinding.podioListView.recyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        articlesViewAdapter = new ArticlesViewAdapter(
                new ArrayList<>(0),
                mViewModel
        );
        recyclerView.setAdapter(articlesViewAdapter);

    }

}
