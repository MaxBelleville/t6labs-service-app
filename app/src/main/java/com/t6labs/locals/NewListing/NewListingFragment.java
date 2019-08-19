package com.t6labs.locals.NewListing;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputEditText;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;
import com.t6labs.locals.Common.DescriptionDto;
import com.t6labs.locals.Common.NewListingRequest;
import com.t6labs.locals.MainActivity;
import com.t6labs.locals.R;
import com.t6labs.locals.services.LocalsService;
import com.t6labs.locals.services.RetrofitInstance;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

//TODO implement view model
public class NewListingFragment extends Fragment {

    @BindView(R.id.carouselView)
    CarouselView carouselView;

    @BindView(R.id.newLTEdit)
    TextInputEditText newLTEdit;

    @BindView(R.id.newSDEdit)
    TextInputEditText newSDEdit;

    @BindView(R.id.newLDEdit)
    TextInputEditText newLDEdit;

    @BindView(R.id.newPriceEdit)
    TextInputEditText newPriceEdit;

    ArrayList<Bitmap> images = new ArrayList<Bitmap>();

    //TODO create Util method to convert px to dp, dp to px
    int imageHeight = 120, imageWidth = 120;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.content_new_listing, container, false);
        ButterKnife.bind(this, view);
        //TODO refactor
        Objects.requireNonNull((MainActivity) getActivity()).setActionBarTitle(getActivity().getString(R.string.title_new_listing), true);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        carouselView.setImageListener(imageListener);
    }

    @OnClick(R.id.newImage)
    void newImage(View view) {
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(photoPickerIntent, 1);
        carouselView.setVisibility(View.VISIBLE);
    }

    @OnClick(R.id.postNew)
    void postNew(View view) {
        String title = newLTEdit.getText().toString();
        String shortDesc = newSDEdit.getText().toString();
        LocalsService localsService = RetrofitInstance.getRetrofitInstance().create(LocalsService.class);
        Call<DescriptionDto> descriptionDtoCall = localsService.postLocalListing(new NewListingRequest(title, shortDesc));
        descriptionDtoCall.enqueue(new Callback<DescriptionDto>() {
            @Override
            public void onResponse(@NonNull Call<DescriptionDto> call, @NonNull Response<DescriptionDto> response) {
                Log.d("Tag", response.body().getTitle());
                //TODO go to confirmation screen
            }

            @Override
            public void onFailure(@NonNull Call<DescriptionDto> call, @NonNull Throwable t) {
                //TODO handle error
                Log.d("Error",t.getMessage());
            }
        });
    }

    @Override
    public void onActivityResult(int reqCode, int resultCode, Intent data) {
        super.onActivityResult(reqCode, resultCode, data);

        if (resultCode == getActivity().RESULT_OK) {
            imageHeight = carouselView.getHeight();
            imageWidth = carouselView.getWidth();
            try {
                final Uri imageUri = data.getData();
                final InputStream imageStream = getActivity().getContentResolver().openInputStream(imageUri);
                final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                images.add(selectedImage);
                updateCarsouel();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        } else {
            updateCarsouel();
            Toast.makeText(getActivity(), "You haven't picked Image", Toast.LENGTH_LONG).show();
        }
    }

    private void updateCarsouel() {
        if (!images.isEmpty()) {
            carouselView.setVisibility(View.VISIBLE);
            carouselView.setPageCount(images.size());
        } else
            carouselView.setVisibility(View.GONE);
    }

    ImageListener imageListener = (int pos, ImageView imageView) ->
            imageView.setImageBitmap(Bitmap.createScaledBitmap(images.get(pos), imageWidth, imageHeight, true));
}
