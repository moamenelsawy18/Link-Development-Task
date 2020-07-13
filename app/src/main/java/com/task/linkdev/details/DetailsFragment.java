package com.task.linkdev.details;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.task.linkdev.R;
import com.task.linkdev.data.Article;
import com.task.linkdev.databinding.DetailsFragmentBinding;

public class DetailsFragment extends Fragment {

    private DetailsViewModel mViewModel;
    private DetailsFragmentBinding mViewDataBinding;
    private static final String EXTRA_DATA = "EXTRA_ARTICLE";


    public static DetailsFragment newInstance(Article article) {
        Bundle args = new Bundle();
        args.putSerializable(EXTRA_DATA, article);
        DetailsFragment fragment = new DetailsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onStart() {
        super.onStart();
        mViewModel.start((Article) getArguments().getSerializable("EXTRA_ARTICLE"));
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        final View root = inflater.inflate(R.layout.details_fragment, container, false);
        if (mViewDataBinding == null) {
            mViewDataBinding = DetailsFragmentBinding.bind(root);
        }

        mViewModel = ViewModelProviders.of(this).get(DetailsViewModel.class);


        mViewDataBinding.setListener(getArticleDetailsListner());
        mViewDataBinding.setViewModel(mViewModel);

        return mViewDataBinding.getRoot();
    }

    private ArticleDetailsListener getArticleDetailsListner() {
        return new ArticleDetailsListener() {
            @Override
            public void openWebsiteClicked() {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(mViewModel.article.get().getUrl()));
                startActivity(browserIntent);
            }
        };
    }

}
