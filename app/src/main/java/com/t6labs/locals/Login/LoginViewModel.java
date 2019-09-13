package com.t6labs.locals.Login;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.t6labs.locals.Common.LiveDataFactory;
import com.t6labs.locals.Common.LoginRequest;
import com.t6labs.locals.Common.UserProfileDto;
import com.t6labs.locals.services.LocalsService;
import com.t6labs.locals.services.RetrofitInstance;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginViewModel extends ViewModel {
    private static final String TAG = LoginViewModel.class.getName();

    private final MutableLiveData<Boolean> isLoggedIn = LiveDataFactory.newMutableLiveData(Boolean.FALSE);

    void setLoggedIn(@NonNull Boolean isLoggedIn) {
        this.isLoggedIn.setValue(isLoggedIn);
    }

    LiveData<Boolean> getIsLoggedIn() {
        return isLoggedIn;
    }

    FacebookCallback<LoginResult> getFacebookCallback() {
        return new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                //todo handle facebook signin
                //login("fb","lskdjfls");
                setLoggedIn(Boolean.TRUE);
                
            }

            @Override
            public void onCancel() {
                // TODO: 2019-09-03 handle cancel
            }

            @Override
            public void onError(FacebookException exception) {
                // TODO: 2019-09-03 handle error
                setLoggedIn(Boolean.FALSE);
            }
        };
    }

    // TODO: 2019-09-13 don't pass context here, use provider design pattern
    void login(@NonNull Context context, @NonNull String type, @NonNull String token) {
        LocalsService localsService = RetrofitInstance.getRetrofitInstance(context).create(LocalsService.class);
        Call<UserProfileDto> loginCall = localsService.login(token,new LoginRequest(type));
        loginCall.enqueue(new Callback<UserProfileDto>() {
            @Override
            public void onResponse(Call<UserProfileDto> call, Response<UserProfileDto> response) {
                setLoggedIn(true);
            }

            @Override
            public void onFailure(Call<UserProfileDto> call, Throwable t) {
                Log.d(TAG, "onFailure: login call failed");
            }
        });
    }
}
