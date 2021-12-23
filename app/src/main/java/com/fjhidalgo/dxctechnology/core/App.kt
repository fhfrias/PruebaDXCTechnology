package com.fjhidalgo.dxctechnology.core

import android.text.TextUtils
import android.util.Log
import androidx.multidex.MultiDexApplication
import com.android.volley.*
import com.android.volley.toolbox.Volley

class App: MultiDexApplication() {

    companion object {
        var instance: App = App()
        private val TAG = App::class.java.simpleName
    }

    override fun onCreate() {
        super.onCreate()

        instance = this

    }

    var requestQueue: RequestQueue? = null
        get() {
            if (field == null) {
                field = Volley.newRequestQueue(applicationContext)
                return field
            }
            return field
        }

    fun <T> addToRequestQueue(req: Request<T>, tag: String) {
        req.setShouldCache(false);
        req.retryPolicy = DefaultRetryPolicy(30 * 1000, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT)
        req.tag = if (TextUtils.isEmpty(tag)) TAG else tag
        requestQueue?.add(req)
        Log.d("Volley", tag)
    }
}