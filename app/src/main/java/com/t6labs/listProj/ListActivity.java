package com.t6labs.listProj;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public class ListActivity extends AppCompatActivity {
    private static Services services;
    private static RecyclerView myList;

    private static Context appContext;
    public static Context getAppContext(){
        return appContext;
    }
    public static RecyclerView getList() {
        return myList;
    }
    public static Services getServices(){
        return services;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        Resources res = getResources();
        services = new Services(this, "http://20.37.136.227/test");
        myList = (RecyclerView) findViewById(R.id.myList);
        appContext = getApplicationContext();
        LinearLayoutManager layout = new LinearLayoutManager(this);
        myList.setLayoutManager(layout);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(myList.getContext(), layout.getOrientation());
        myList.addItemDecoration(dividerItemDecoration);
        myList.setAdapter(new ServicesAdapter());

    }
}
