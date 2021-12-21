package com.fjhidalgo.dxctechnology.module.main.repository

object MainActivityRepository: MainActivityDataSource {

    private var instance: MainActivityRepository? = null

    fun getInstance(): MainActivityRepository? {

        if (instance == null){
            instance = this
        }

        return instance
    }


}