package com.task.linkdev.main;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.navigation.NavigationView;
import com.task.linkdev.R;
import com.task.linkdev.data.Article;
import com.task.linkdev.details.DetailsActivity;
import com.task.linkdev.details.DetailsFragment;
import com.task.linkdev.utils.ActivityAnimationUtils;



public class MainActivity extends AppCompatActivity implements Navigator , NavigationView.OnNavigationItemSelectedListener{

    private static final String EXTRA_DATA = "EXTRA_ARTICLE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);

        ImageView imageView = findViewById(R.id.navigation_icon);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawer.openDrawer(Gravity.LEFT);
            }
        });

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        toolbar.setNavigationIcon(null);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        loadMainFragmnt();
    }

    private void loadMainFragmnt() {
        ArticlesFragment articlesFragment = new ArticlesFragment();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.fragment, articlesFragment, articlesFragment.getClass().getName()).commit();
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        String text = "";

        if (id == R.id.explore)
            text = "Explore";
        else if (id == R.id.live)
            text = "Live Chat";
        else if (id == R.id.gallery)
            text = "Gallery";
        else if (id == R.id.wishlist)
            text = "Wish List";
        else if (id == R.id.magazine)
            text = "E-Magazine";

        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
        
        return true;
    }

    @Override
    public void openDetails(Article article) {
        Intent intent = new Intent(this, DetailsActivity.class);
        intent.putExtra(EXTRA_DATA , article);
        ActivityAnimationUtils.startActivityWithSlideInBottom(this, intent);
    }


}
