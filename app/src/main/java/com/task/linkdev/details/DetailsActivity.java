package com.task.linkdev.details;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentTransaction;

import com.task.linkdev.R;
import com.task.linkdev.data.Article;
import com.task.linkdev.utils.ActivityAnimationUtils;


public class DetailsActivity extends AppCompatActivity {

    private static final String EXTRA_DATA = "EXTRA_ARTICLE";

    @Override
    protected void onPause() {
        super.onPause();
        if (isFinishing()) {
            ActivityAnimationUtils.slideOutBottomAndFadeOut(this);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details_activity);

        getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ImageView back = findViewById(R.id.navigation_icon);
        back.setImageDrawable(getResources().getDrawable(R.drawable.back));
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        openDetailsFragment();
    }

    private void openDetailsFragment() {
        DetailsFragment detailsFragment = DetailsFragment.newInstance((Article) getIntent().getSerializableExtra(EXTRA_DATA));
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.fragment, detailsFragment, detailsFragment.getClass().getName()).commit();
    }
}
