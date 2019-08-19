package com.t6labs.locals.Home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.t6labs.locals.Common.LocalsDto;
import com.t6labs.locals.MainActivity;
import com.t6labs.locals.R;
import com.t6labs.locals.services.LocalsService;
import com.t6labs.locals.services.RetrofitInstance;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
    private HomeViewModel viewModel;

    @NonNull
    private Observer<List<LocalsDto>> listingObserver = this::onListingUpdated;

    public HomeFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.content_home, container, false);
        unbinder = ButterKnife.bind(this, view);

        //TODO refactor
       ((MainActivity) requireActivity()).setActionBarTitle("Local Listings", false);
       ((MainActivity) requireActivity()).bottomNavVisibility(true);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        LocalsService localsService = RetrofitInstance.getRetrofitInstance().create(LocalsService.class);
        Call<ArrayList<LocalsDto>> call = localsService.getLocalsListing();
        viewModel.getListing().observe(this, listingObserver);

        call.enqueue(new Callback<ArrayList<LocalsDto>>() {
            @Override
            public void onResponse(@NonNull Call<ArrayList<LocalsDto>> call, @NonNull Response<ArrayList<LocalsDto>> response) {
                viewModel.setListing(response.body());
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
        viewModel.getListing().removeObserver(listingObserver);
    }

    private void onListingUpdated(List<LocalsDto> listing) {
        //TODO don't init recycler view here, only update data
        initLocalsListingRecyclerView(listing);
    }

    private void initLocalsListingRecyclerView(final List<LocalsDto> localsDto) {

        localsListAdapter = new LocalsListAdapter(localsDto, (v, position) -> {
            String id = localsDto.get(position).getId();
            HomeFragmentDirections.ActionHomeFragmentToDescriptionFragment actionHomeFragmentToDescriptionFragment = HomeFragmentDirections.actionHomeFragmentToDescriptionFragment();
            actionHomeFragmentToDescriptionFragment.setListingId(id);
            Navigation.findNavController(v).navigate(actionHomeFragmentToDescriptionFragment);
        });

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        localsListing.setLayoutManager(layoutManager);
        localsListing.setAdapter(localsListAdapter);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(Objects.requireNonNull(getActivity()), layoutManager.getOrientation());

        //TODO check for null
        dividerItemDecoration.setDrawable(ContextCompat.getDrawable(getContext(), R.drawable.divider));
        localsListing.addItemDecoration(dividerItemDecoration);
    }
}