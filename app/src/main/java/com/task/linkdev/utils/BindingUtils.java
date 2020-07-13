package com.task.linkdev.utils;

import android.util.TypedValue;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.task.linkdev.R;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;


public class BindingUtils {

    @BindingAdapter({"imageUrl"})
    public static void loadRoundedImage(ImageView view, String url) {
        Glide.with(view)
                .load(url)
                .apply(new RequestOptions()
                        .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                .placeholder(R.drawable.placeholder))
                .transition(withCrossFade())
                .into(view);
    }
}
