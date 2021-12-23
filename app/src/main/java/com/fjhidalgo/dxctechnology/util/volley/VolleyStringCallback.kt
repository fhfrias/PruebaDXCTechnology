package com.fjhidalgo.dxctechnology.util.volley

import com.android.volley.VolleyError

interface VolleyStringCallback {

    fun onResponse(response: String)

    fun onError(error: VolleyError)
}