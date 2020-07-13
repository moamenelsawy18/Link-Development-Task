package com.task.linkdev.utils;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;

import com.task.linkdev.R;


public class ActivityAnimationUtils {

    public static void slideOutBottomAndFadeOut(Activity activity) {
        activity.overridePendingTransition(R.anim.fade_in, R.anim.slide_out_bottom_fade_out);
    }

    public static void startActivityWithSlideInBottom(Context context, Intent intent) {
        context.startActivity(intent, ActivityOptions.makeCustomAnimation(context,
                R.anim.slide_in_bottom, R.anim.fade_out).toBundle());
    }
}
