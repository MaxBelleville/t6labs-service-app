package com.t6labs.locals.Common;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

public class LiveDataFactory {

    @NonNull
    public static <T> MutableLiveData<T> newMutableLiveData(T initialValue) {
        final MutableLiveData<T> liveData = new MutableLiveData<>();
        liveData.setValue(initialValue);
        return liveData;
    }

}