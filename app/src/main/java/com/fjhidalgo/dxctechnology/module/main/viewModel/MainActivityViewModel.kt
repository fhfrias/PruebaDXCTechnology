package com.fjhidalgo.dxctechnology.module.main.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.android.volley.VolleyError
import com.fjhidalgo.dxctechnology.data.network.model.FlickrResponse
import com.fjhidalgo.dxctechnology.data.network.model.ImageModel
import com.fjhidalgo.dxctechnology.module.base.view_model.BaseViewModel
import com.fjhidalgo.dxctechnology.module.main.repository.MainActivityRepository
import com.fjhidalgo.dxctechnology.util.volley.VolleyErrorUtil
import com.fjhidalgo.dxctechnology.util.volley.VolleyStringCallback
import org.json.JSONObject

class MainActivityViewModel: BaseViewModel() {

    val imageListResultLiveData: MutableLiveData<List<ImageModel>> = MutableLiveData<List<ImageModel>>()
    val errorListResultLiveData: MutableLiveData<Boolean> = MutableLiveData<Boolean>()
    val repository = MainActivityRepository.getInstance()

    fun getSearchWord(word: String){

        repository!!.getSearchWord(word, object : VolleyStringCallback {

            override fun onResponse(response: String) {

                val jsonObjectResponse = JSONObject(response.substring(response.indexOf("{"), response.lastIndexOf("}") + 1))
                val flickrResponse = gson.fromJson(jsonObjectResponse.toString(), FlickrResponse::class.java)
                imageListResultLiveData.value = flickrResponse.listImages
            }

            override fun onError(error: VolleyError) {
                VolleyErrorUtil.handleError(error)
                errorListResultLiveData.value = true
            }
        })
    }
}