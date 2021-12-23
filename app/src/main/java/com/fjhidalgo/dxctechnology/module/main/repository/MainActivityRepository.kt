package com.fjhidalgo.dxctechnology.module.main.repository

import com.android.volley.Request
import com.android.volley.Response
import com.fjhidalgo.dxctechnology.core.App
import com.fjhidalgo.dxctechnology.data.network.ApiEndPointHelper
import com.fjhidalgo.dxctechnology.util.volley.*
import org.json.JSONObject

object MainActivityRepository: MainActivityDataSource {

    private var instance: MainActivityRepository? = null

    fun getInstance(): MainActivityRepository? {

        if (instance == null){
            instance = this
        }

        return instance
    }

    override fun getSearchWord(word: String, callback: VolleyStringCallback) {

        val url = ApiEndPointHelper.getSearchWord(word)

        val jsonReq = object : StringVolleyRequest(
            Method.GET, url,
            Response.Listener { callback.onResponse(it) },
            Response.ErrorListener { callback.onError(it) }) {
            override fun getHeaders(): Map<String, String> {

                val headers = HashMap<String, String>()

                return headers
            }

        }

        App.instance.addToRequestQueue(jsonReq, "getSearchWord")
    }
}