package com.t6labs.locals;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

//TODO rename activity to LocalsListingActivity and move all common methods to MainActivity
//TODO extend LocalsListingActivity from main activity
public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setupNavigation();
    }

    //setup navigation
    private void setupNavigation() {
        NavController navController = Navigation.findNavController(this,R.id.nav_host_fragment);
        BottomNavigationView bottomNavigationView = findViewById(R.id.navigationView);
        setupWithNavController(bottomNavigationView,navController);
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
            }

            return true;
        });

        NavigationUI.setupWithNavController(bottomNavigationView,navController);
    }

}