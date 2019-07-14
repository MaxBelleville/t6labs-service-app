package com.t6labs.locals;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import com.t6labs.locals.adapters.LocalsListAdapter;
import com.t6labs.locals.models.LocalsDto;
import com.t6labs.locals.services.LocalsService;
import com.t6labs.locals.services.RetrofitInstance;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {

    @BindView(R.id.myList)
    RecyclerView localsListing;

    private LocalsListAdapter localsListAdapter;
    private Unbinder unbinder;

    public HomeFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.content_main, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        LocalsService localsService = RetrofitInstance.getRetrofitInstance().create(LocalsService.class);
        Call<ArrayList<LocalsDto>> call = localsService.getLocalsListing();

        call.enqueue(new Callback<ArrayList<LocalsDto>>() {
            @Override
            public void onResponse(@NonNull Call<ArrayList<LocalsDto>> call, @NonNull Response<ArrayList<LocalsDto>> response) {
                initLocalsListingRecyclerView(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<ArrayList<LocalsDto>> call, @NonNull Throwable t) {
                Log.d(HomeFragment.this.getClass().getName(), t.getMessage());
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private void initLocalsListingRecyclerView(final ArrayList<LocalsDto> localsDto) {

         localsListAdapter = new LocalsListAdapter(localsDto, (v, position) -> {
             String id = localsDto.get(position).getId();
             HomeFragmentDirections.ActionHomeFragmentToDescriptionFragment actionHomeFragmentToDescriptionFragment = HomeFragmentDirections.actionHomeFragmentToDescriptionFragment();
             actionHomeFragmentToDescriptionFragment.setListingId(id);
             //final NavDirections directions = HomeFragmentDirections.actionHomeFragmentToDescriptionFragment(id);

             //NavController navController = new NavController(v.getContext());
             //navController.navigate(directions);
             Navigation.findNavController(v).navigate(actionHomeFragmentToDescriptionFragment);
         });

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        localsListing.setLayoutManager(layoutManager);
        localsListing.setAdapter(localsListAdapter);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getActivity(), layoutManager.getOrientation());

        //TODO check for null
        dividerItemDecoration.setDrawable(ContextCompat.getDrawable(getContext(), R.drawable.divider));
        localsListing.addItemDecoration(dividerItemDecoration);
    }
}