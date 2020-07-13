package com.task.linkdev.main;

import androidx.databinding.ViewDataBinding;

import com.task.linkdev.BR;
import com.task.linkdev.R;
import com.task.linkdev.base.BaseRecyclerViewAdapter;
import com.task.linkdev.data.Article;

import java.util.List;

public class ArticlesViewAdapter extends BaseRecyclerViewAdapter<Article, ArticlesViewModel, ArticlesViewAdapter.PodioViewHolder> {

    public ArticlesViewAdapter(List<Article> dataSet, ArticlesViewModel viewModel) {
        super(dataSet, viewModel);
    }

    @Override
    protected PodioViewHolder onCreateViewHolder(ViewDataBinding binding) {
        return new PodioViewHolder(binding);
    }

    @Override
    protected int getLayoutIdForPosition(int position) {
        return R.layout.item_article;
    }

    @Override
    protected int getLayoutIdForLoading(int position) {
        return R.layout.item_shimmer_article;
    }


    protected class PodioViewHolder extends BaseRecyclerViewAdapter.BaseViewHolder implements ArticleItemListener {

        PodioViewHolder(ViewDataBinding binding) {
            super(binding);
        }

        @Override
        public void bind(Object item) {
            binding.setVariable(BR.listener, this);
            binding.setVariable(BR.viewModel, mViewModel);
            super.bind(item);
        }

        @Override
        public void onItemClicked(Article item) {
            mViewModel.openArticleDetails(item);
        }
    }


}