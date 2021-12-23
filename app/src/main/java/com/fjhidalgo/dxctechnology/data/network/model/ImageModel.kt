package com.fjhidalgo.dxctechnology.data.network.model

import com.fjhidalgo.dxctechnology.module.base.model.BaseModel
import com.google.gson.JsonObject
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import org.json.JSONObject

class ImageModel: BaseModel() {

    @Expose
    @SerializedName("title")
    var title: String? = null

    @Expose
    @SerializedName("author")
    var author: String? = null

    @Expose
    @SerializedName("published")
    var published: String? = null

    @Expose
    @SerializedName("description")
    var description: String? = null

    @Expose
    @SerializedName("media")
    var media: JsonObject? = null
}