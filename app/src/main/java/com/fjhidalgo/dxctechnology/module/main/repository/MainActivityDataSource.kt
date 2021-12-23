package com.fjhidalgo.dxctechnology.module.main.repository

import com.fjhidalgo.dxctechnology.util.volley.VolleyStringCallback

interface MainActivityDataSource {

    fun getSearchWord(word: String, callback: VolleyStringCallback)
}