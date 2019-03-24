package com.t6labs.locals;

import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.t6labs.locals.models.LocalsDto;
import com.t6labs.locals.services.LocalsService;
import com.t6labs.locals.services.RetrofitInstance;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

//TODO rename activity to LocalsListingActivity and move all common methods to MainActivity
//TODO extend LocalsListingActivity from main activity
public class MainActivity extends AppCompatActivity {

    private RecyclerView localsListing;
    private LocalsListAdapter localsListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LocalsService localsService = RetrofitInstance.getRetrofitInstance().create(LocalsService.class);
        Call<ArrayList<LocalsDto>> call = localsService.getLocalsListing();

        call.enqueue(new Callback<ArrayList<LocalsDto>>() {
            @Override
            public void onResponse(Call<ArrayList<LocalsDto>> call, Response<ArrayList<LocalsDto>> response) {
                initLocalsListingRecyclerView(response.body());
            }

            @Override
            public void onFailure(Call<ArrayList<LocalsDto>> call, Throwable t) {
                //TODO handle errors
            }
        });

        initNavigation();
    }

    //setup Recyclerview
    private void initLocalsListingRecyclerView(ArrayList<LocalsDto> localsDtos) {
        localsListing = (RecyclerView) findViewById(R.id.myList);
        localsListAdapter = new LocalsListAdapter(localsDtos);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        localsListing.setLayoutManager(layoutManager);
        localsListing.setAdapter(localsListAdapter);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getApplicationContext(), layoutManager.getOrientation());

        //TODO check for null
        dividerItemDecoration.setDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.divider));
        localsListing.addItemDecoration(dividerItemDecoration);
    }

    //setup navigation
    private void initNavigation() {
        BottomNavigationView bottomNavigationView = findViewById(R.id.navigationView);
    }
}
