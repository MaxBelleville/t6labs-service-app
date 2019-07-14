package com.t6labs.locals;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

//TODO rename activity to LocalsListingActivity and move all common methods to MainActivity
//TODO extend LocalsListingActivity from main activity
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupNavigation();
    }

    //setup navigation
    private void setupNavigation() {
        NavController navController = Navigation.findNavController(this,R.id.nav_host_fragment);
        BottomNavigationView bottomNavigationView = findViewById(R.id.navigationView);
        setupWithNavController(bottomNavigationView,navController);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                if (menuItem.getTitle().equals("Account")) {
                    Intent intent = new Intent(getApplicationContext(), NewListing.class);
                    startActivity(intent);
                }
                return true;
            }
        });
    }

    private static void setupWithNavController(@NonNull final BottomNavigationView bottomNavigationView,
                                               @NonNull final NavController navController) {

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                //TODO handle bottom navigation item click events

                return false;
            }
        });

        NavigationUI.setupWithNavController(bottomNavigationView,navController);
    }

}