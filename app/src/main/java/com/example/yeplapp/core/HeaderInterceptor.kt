package com.example.yeplapp.core

import com.example.yeplapp.application.AppConstants
import okhttp3.Interceptor
import okhttp3.Response

class HeaderInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder().addHeader(
            "Authorization", "Bearer ${AppConstants.BEARER_TOKEN}"
        ).build()
        return chain.proceed(request)
    }
}