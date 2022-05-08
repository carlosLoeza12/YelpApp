package com.example.yeplapp.application

import com.example.yeplapp.core.HeaderInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

object AppConstants {
    const val BASE_URL = "https://api.yelp.com/v3/businesses/"
    const val BEARER_TOKEN = "SWp-u5-H6s2VaDCoSIJUkYxAQ9_qSa8pQvMZrbYfejt09lBBrOATc1k5k_m_d6ixPvC4LRCesIyqKSHmEqATcu-_PEZJ8Tdw37AdH7qiNl1DBAIltrkjHUlM0WxxYnYx"
    // Create Logger
    private val logger = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    var okHttp = OkHttpClient.Builder().addInterceptor(HeaderInterceptor()).addInterceptor(logger)
    //Request permission
    const val  PERMISSION_LOCATION_REQUEST_CODE = 1
}