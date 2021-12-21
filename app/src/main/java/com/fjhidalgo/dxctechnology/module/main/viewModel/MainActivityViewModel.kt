package com.fjhidalgo.dxctechnology.module.main.viewModel

import android.util.Log
import androidx.core.util.Pair
import androidx.lifecycle.MutableLiveData
import com.fjhidalgo.dxctechnology.module.main.repository.MainActivityRepository

class MainActivityViewModel {

    //val checklistResultLiveData: MutableLiveData<List<ResponseModel>> = MutableLiveData<List<ResponseModel>>()
    val repository = MainActivityRepository.getInstance()


}