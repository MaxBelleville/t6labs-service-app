package com.t6labs.locals;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Objects;

//TODO rename activity to LocalsListingActivity and move all common methods to MainActivity
//TODO extend LocalsListingActivity from main activity
public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TextView toolbarTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupToolbar();
        setupNavigation();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //context menu
        getMenuInflater().inflate(R.menu.menu_main, menu);

        //search
        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchManager searchManager = (SearchManager) MainActivity.this.getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = null;
        if (searchItem != null) {
            searchView = (SearchView) searchItem.getActionView();
        }
        if (searchView != null) {
            searchView.setSearchableInfo(searchManager.getSearchableInfo(MainActivity.this.getComponentName()));
        }

        return super.onCreateOptionsMenu(menu);
    }

    //setup toolbar
    private void setupToolbar() {
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(false);
        toolbarTitle = toolbar.findViewById(R.id.toolbar_title);
    }

    //setup navigation
    private void setupNavigation() {
        NavController navController = Navigation.findNavController(this,R.id.nav_host_fragment);
        BottomNavigationView bottomNavigationView = findViewById(R.id.navigationView);
        setupWithNavController(bottomNavigationView,navController);
    }
    public void bottomNavVisibility(boolean isVisible){
        BottomNavigationView bottomNavigationView = findViewById(R.id.navigationView);
        bottomNavigationView.setVisibility(isVisible ? View.VISIBLE : View.GONE);
    }
    public void setActionBarTitle(@NonNull String title, boolean showBackButton) {
        toolbarTitle.setText(title);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(showBackButton);
    }

    private void setupWithNavController(@NonNull final BottomNavigationView bottomNavigationView,
                                               @NonNull final NavController navController) {

        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.localsHomeFragment:
                    break;
                case R.id.navigation_map:
                    break;
                case R.id.navigation_account:
                    break;
                case R.id.navigation_favourite:
                    break;
            }
            return true;
        });

        NavigationUI.setupWithNavController(bottomNavigationView,navController);
    }

}