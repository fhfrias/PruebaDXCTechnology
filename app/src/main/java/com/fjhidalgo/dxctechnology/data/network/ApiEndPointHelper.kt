package com.fjhidalgo.dxctechnology.data.network

object ApiEndPointHelper {

    fun getSearchWord(tag: String): String {

        return String.format(ApiEndPoint.GET_SEARCH_WORD, tag)
    }
}