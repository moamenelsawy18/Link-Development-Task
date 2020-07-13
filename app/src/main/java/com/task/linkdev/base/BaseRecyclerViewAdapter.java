package com.task.linkdev.base;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import com.task.linkdev.BR;

import java.util.List;

public abstract class BaseRecyclerViewAdapter<M, VM extends BaseViewModel,
        VH extends BaseRecyclerViewAdapter.BaseViewHolder> extends RecyclerView.Adapter<VH> {
    protected List<M> mDataSet;
    protected VM mViewModel;
    private boolean mIsPaging = false;


    protected BaseRecyclerViewAdapter(List<M> dataSet, VM viewModel) {
        mDataSet = dataSet;
        mViewModel = viewModel;
    }

    public void replaceData(List<M> dataSet) {
        setData(dataSet);

    }

    protected void setData(List<M> dataSet) {
        mDataSet = dataSet;
        notifyDataSetChanged();
    }

    public void setIsPaging(boolean isPaging) {
        this.mIsPaging = isPaging;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater =
                LayoutInflater.from(parent.getContext());
        ViewDataBinding binding = DataBindingUtil.inflate(
                layoutInflater, viewType, parent, false);
        return onCreateViewHolder(binding);
    }

    @Override
    public int getItemViewType(int position) {
        if (position >= mDataSet.size())
            return getLayoutIdForLoading(position);

        return getLayoutIdForPosition(position);
    }

    protected abstract VH onCreateViewHolder(ViewDataBinding binding);

    protected abstract int getLayoutIdForPosition(int position);

    protected abstract int getLayoutIdForLoading(int position);

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        holder.bind(mDataSet.get(position));
    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }

    public abstract class BaseViewHolder extends RecyclerView.ViewHolder {

        public final ViewDataBinding binding;

        protected BaseViewHolder(ViewDataBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Object item) {
            binding.setVariable(BR.item, item);
            binding.executePendingBindings();
        }

    }

}
