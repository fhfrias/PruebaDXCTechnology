package com.fjhidalgo.dxctechnology.data.network

interface ApiEndPoint {

    companion object {

        val BASE_URL = "https://api.flickr.com/services/feeds"
        val GET_SEARCH_WORD = "$BASE_URL/photos_public.gne?format=json&tags=%1\$s"
    }
}