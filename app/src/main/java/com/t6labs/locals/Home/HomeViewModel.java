package com.t6labs.locals.Home;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.t6labs.locals.Common.LocalsDto;

import java.util.List;

 class HomeViewModel extends ViewModel {

    private MutableLiveData<List<LocalsDto>> listMutableLiveData = new MutableLiveData<>();

     LiveData<List<LocalsDto>> getListing() {
        return listMutableLiveData;
    }

     void setListing(List<LocalsDto> listing) {
        listMutableLiveData.postValue(listing);
    }
}