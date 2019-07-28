package com.t6labs.locals.ListDescription;

import android.os.Bundle;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;
import com.t6labs.locals.Dtos.DescriptionDto;
import com.t6labs.locals.MainActivity;
import com.t6labs.locals.R;
import com.t6labs.locals.services.LocalsService;
import com.t6labs.locals.services.RetrofitInstance;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.internal.EverythingIsNonNull;

public class ListingDescriptionFragment extends Fragment {

    //TODO replace with images from api
    int[] sampleImages = {R.drawable.image_1, R.drawable.image_2, R.drawable.image_3};

    @BindView(R.id.username)
    TextView userName;

    @BindView(R.id.listingTitle)
    TextView title;

    @BindView(R.id.description)
    TextView listingDescription;

    @BindView(R.id.carouselView)
    CarouselView carouselView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private String listingId;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.content_description, container, false);
        ButterKnife.bind(this, view);

        //TODO refactor
        Objects.requireNonNull((MainActivity) getActivity()).setActionBarTitle("Listing Description", true);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final ListingDescriptionFragmentArgs args = ListingDescriptionFragmentArgs.fromBundle(Objects.requireNonNull(getArguments()));

        listingId = args.getListingId();

        carouselView.setImageListener(imageListener);
        carouselView.setPageCount(sampleImages.length);

        LocalsService localsService = RetrofitInstance.getRetrofitInstance().create(LocalsService.class);
        Call<DescriptionDto> descriptionDtoCall = localsService.getLocalListingDescription(listingId);

        descriptionDtoCall.enqueue(new Callback<DescriptionDto>() {
            @Override
            @EverythingIsNonNull
            public void onResponse(Call<DescriptionDto> call, Response<DescriptionDto> response) {
                populateDescription(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<DescriptionDto> call,@NonNull Throwable t) {
                Log.d("ERROR", t.getMessage());
            }
        });

    }

    private ImageListener imageListener = (position, imageView) -> imageView.setImageResource(sampleImages[position]);

    private void populateDescription(@NonNull DescriptionDto responseData) {
        userName.setText(responseData.getUsername());
        title.setText(responseData.getTitle());
        listingDescription.setText(responseData.getDescription());
    }
}
