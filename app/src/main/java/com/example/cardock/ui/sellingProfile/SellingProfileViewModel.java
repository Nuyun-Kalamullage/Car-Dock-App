package com.example.cardock.ui.sellingProfile;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SellingProfileViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public SellingProfileViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is gallery fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}