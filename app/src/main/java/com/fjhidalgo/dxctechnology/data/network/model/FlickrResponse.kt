package com.fjhidalgo.dxctechnology.data.network.model

import com.fjhidalgo.dxctechnology.module.base.model.BaseModel
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class FlickrResponse: BaseModel() {

    @Expose
    @SerializedName("items")
    var listImages: List<ImageModel>? = null
}