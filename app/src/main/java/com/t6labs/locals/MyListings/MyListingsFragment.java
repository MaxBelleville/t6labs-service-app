package com.t6labs.locals.MyListings;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.t6labs.locals.Common.LocalsDto;
import com.t6labs.locals.Home.HomeFragmentDirections;
import com.t6labs.locals.Home.HomeListAdapter;
import com.t6labs.locals.MainActivity;
import com.t6labs.locals.R;
import com.t6labs.locals.services.LocalsService;
import com.t6labs.locals.services.RetrofitInstance;

import java.util.ArrayList;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import retrofit2.Call;

public class MyListingsFragment extends Fragment {

    @BindView(R.id.myList)
    RecyclerView myList;
    private Unbinder unbinder;
    private MyListAdapter localsListAdapter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.content_my_listings, container, false);
        unbinder=ButterKnife.bind(this, view);

        //TODO refactor
        Objects.requireNonNull((MainActivity) getActivity()).setActionBarTitle("My Listings", true);
        return view;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        localsListAdapter = new MyListAdapter();

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        myList.setLayoutManager(layoutManager);
        myList.setAdapter(localsListAdapter);
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
