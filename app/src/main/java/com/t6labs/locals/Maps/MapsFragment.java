package com.t6labs.locals.Maps;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.t6labs.locals.MainActivity;
import com.t6labs.locals.R;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MapsFragment extends Fragment {

    @BindView(R.id.mapFrameLayout)
    FrameLayout mapLayout;

    private Unbinder unbinder;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.content_maps, container, false);
        unbinder = ButterKnife.bind(this, view);

        //TODO refactor
        Objects.requireNonNull((MainActivity) getActivity()).setActionBarTitle("Map Lookup", true);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initGoogleMaps();
    }

    private void initGoogleMaps() {
        final SupportMapFragment mapFragment = SupportMapFragment.newInstance();

        getChildFragmentManager().beginTransaction().replace(R.id.mapFrameLayout,mapFragment).commit();
        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                Log.d(this.getClass().getName(),"Map ready");
            }
        });
    }
}
