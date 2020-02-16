package com.t6labs.locals.Maps;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.MarkerOptions;
import com.t6labs.locals.MainActivity;
import com.t6labs.locals.R;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MapsFragment extends Fragment implements SeekBar.OnSeekBarChangeListener {

    @BindView(R.id.mapFrameLayout)
    FrameLayout mapLayout;
    @BindView(R.id.seekBar)
    SeekBar seekBar;
    @BindView(R.id.orbitRange)
    TextView orbitRange;
    private Unbinder unbinder;
    private int distance=0;
    private LatLng latLng;
    private Location currentLocation;
    private static final int REQUEST_CODE = 101;
    private FusedLocationProviderClient fusedLocationClient;
    private GoogleMap map;

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
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(getActivity());
         initGoogleMaps();

    }

    private void initGoogleMaps() {
        final SupportMapFragment mapFragment = SupportMapFragment.newInstance();
        getChildFragmentManager().beginTransaction().replace(R.id.mapFrameLayout,mapFragment).commit();

        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                map = googleMap;
                boolean success = map.setMapStyle(
                        MapStyleOptions.loadRawResourceStyle(
                                getActivity(), R.raw.style_json));
                map.getUiSettings().setAllGesturesEnabled(false);
                //TODO: ACTUALLY CACULATE DISTANCE.
                String distStr=String.format("%.2f",(distance+1)/10.0f);
                orbitRange.setText("Orbit Range: "+ distStr+" km");
                map.setOnMapLoadedCallback(new GoogleMap.OnMapLoadedCallback() {
                    @Override
                    public void onMapLoaded() {
                        Log.d(this.getClass().getName(),"Map ready");
                        fetchLocation();
                    }
                });
            }
        });
    }
    private void fetchLocation() {
        if (ActivityCompat.checkSelfPermission(
                getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions( new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_CODE);
            return;
        }
       fusedLocationClient.getLastLocation().
               addOnSuccessListener(getActivity(), location -> {
                   if (location != null) {
                       currentLocation = location;
                       latLng = new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude());
                       MarkerOptions markerOptions = new MarkerOptions().position(latLng);
                       map.animateCamera(CameraUpdateFactory.newLatLng(latLng));
                       float zoom = ((seekBar.getMax()-distance)/8.0f)+12;
                       map.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,zoom));
                       map.addMarker(markerOptions);
                       seekBar.setOnSeekBarChangeListener(this);
                   }
               });
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    fetchLocation();
                }
                break;
        }
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
    distance = i;
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {}

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        String distStr=String.format("%.2f",(distance+1)/10.0f);
        orbitRange.setText("Orbit Range: "+ distStr+" km");
        float zoom = ((seekBar.getMax()-distance)/8.0f)+12;
        map.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, zoom));
    }
}
