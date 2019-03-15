package com.local.t6labs.local;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v7.widget.RecyclerView;
public class MainActivity extends AppCompatActivity {

    private static Services services;
    private static RecyclerView myList;

    private static Context appContext;
    public static Context getAppContext(){
        return appContext;
    }
    public static RecyclerView getList() {
        return myList;
    }
    public static Services getServices(){return services;}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        services = new Services(this, "http://20.37.136.227/test");
        Resources res = getResources();
        myList = (RecyclerView) findViewById(R.id.myList);
        appContext = getApplicationContext();
        LinearLayoutManager layout = new LinearLayoutManager(this);
        myList.setLayoutManager(layout);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(myList.getContext(), layout.getOrientation());
        dividerItemDecoration.setDrawable(ContextCompat.getDrawable(appContext, R.drawable.divider));
        myList.addItemDecoration(dividerItemDecoration);

        initNavigation();
    }

    private void initNavigation() {
        BottomNavigationView bottomNavigationView = findViewById(R.id.navigationView);
    }
}
