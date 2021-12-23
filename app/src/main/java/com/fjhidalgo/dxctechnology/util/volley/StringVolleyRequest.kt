package com.fjhidalgo.dxctechnology.util.volley

import android.text.TextUtils
import com.android.volley.*
import com.android.volley.Response.ErrorListener
import com.android.volley.Response.Listener
import com.android.volley.toolbox.HttpHeaderParser
import com.android.volley.toolbox.StringRequest
import com.fjhidalgo.dxctechnology.core.App
import java.nio.charset.Charset
import kotlin.system.exitProcess

open class StringVolleyRequest (method: Int,
                                 url: String,
                                 private val listener: Listener<String>,
                                 errorListener: ErrorListener)
: StringRequest(method, url, listener, errorListener) {

    override fun getBodyContentType(): String {
        return "application/json"
    }

    override fun parseNetworkResponse(response: NetworkResponse): Response<String> {
        try {

            var cacheEntry: Cache.Entry? = HttpHeaderParser.parseCacheHeaders(response)
            if (cacheEntry == null) {
                cacheEntry = Cache.Entry()
            }
            val cacheHitButRefreshed = (3 * 1000).toLong()
            val cacheExpired = (10 * 60 * 1000).toLong()
            val now = System.currentTimeMillis()
            val softExpire = now + cacheHitButRefreshed
            val ttl = now + cacheExpired
            cacheEntry.data = response.data
            cacheEntry.softTtl = softExpire
            cacheEntry.ttl = ttl
            var headerValue: String? = response.headers?.get("Date")
            if (headerValue != null) {
                cacheEntry.serverDate = HttpHeaderParser.parseDateAsEpoch(headerValue)
            }
            headerValue = response.headers?.get("Last-Modified")
            if (headerValue != null) {
                cacheEntry.lastModified = HttpHeaderParser.parseDateAsEpoch(headerValue)
            }
            cacheEntry.responseHeaders = response.headers

            val jsonString = String(response.data, Charset.forName(HttpHeaderParser.parseCharset(response.headers)))

            return if (TextUtils.isEmpty(jsonString)) {
                Response.success("", HttpHeaderParser.parseCacheHeaders(response))
            } else Response.success(jsonString, cacheEntry)

        } catch (e: Exception) {
            val error = ParseError(e)

            return Response.error(error)
        }

    }



    override fun deliverError(error: VolleyError) {
        super.deliverError(error)

        try {
            if (error.networkResponse != null && error.networkResponse.statusCode == 401 || error.networkResponse.statusCode == 403) {

                val app = App.instance

                exitProcess(0)
            }

            errorListener!!.onErrorResponse(error)
        } catch (ex: Exception) {
            ex.printStackTrace()
        }

    }

    override fun deliverResponse(response: String) {
        listener.onResponse(response)
    }
}