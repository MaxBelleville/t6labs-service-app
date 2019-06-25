package com.t6labs.locals;

import android.os.Bundle;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;
import com.t6labs.locals.models.DescriptionDto;
import com.t6labs.locals.services.LocalsService;
import com.t6labs.locals.services.RetrofitInstance;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.internal.EverythingIsNonNull;

public class ListingDescriptionActivity extends AppCompatActivity {
    CarouselView carouselView;
    int[] sampleImages = {R.drawable.image_1, R.drawable.image_2, R.drawable.image_3};
    @BindView(R.id.username)
    TextView userName;

    @BindView(R.id.listingTitle)
    TextView title;

    @BindView(R.id.description)
    TextView listingDescription;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);

        userName = findViewById(R.id.username);
        title = findViewById(R.id.listingTitle);
        listingDescription = findViewById(R.id.description);

        carouselView = (CarouselView) findViewById(R.id.carouselView);
        carouselView.setPageCount(sampleImages.length);

        carouselView.setImageListener(imageListener);

        Bundle bundle = getIntent().getExtras();

        String listingId = null;

        if (bundle != null) {
            listingId = bundle.getString("id");
        }

        if (listingId != null) {
            LocalsService localsService = RetrofitInstance.getRetrofitInstance().create(LocalsService.class);
            Call<DescriptionDto> descriptionDtoCall = localsService.getLocalListingDescription(listingId);

            descriptionDtoCall.enqueue(new Callback<DescriptionDto>() {
                @Override
                @EverythingIsNonNull
                public void onResponse(Call<DescriptionDto> call, Response<DescriptionDto> response) {
                    populateDescription(response.body());
                }

                @Override
                public void onFailure(Call<DescriptionDto> call, Throwable t) {
                    Log.d("ERROR", t.getMessage());
                }
            });
        }
    }
    private ImageListener imageListener = new ImageListener() {
        @Override
        public void setImageForPosition(int position, ImageView imageView) {
            imageView.setImageResource(sampleImages[position]);
        }
    };
    private void populateDescription(@NonNull DescriptionDto responseData) {
        userName.setText(responseData.getUsername());
        title.setText(responseData.getTitle());
        listingDescription.setText(responseData.getDescription());
    }
}
