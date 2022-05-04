package com.example.yeplapp.repository

import com.example.yeplapp.data.model.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WebService {

    @GET("search")
    suspend fun getBusinesses(
        @Query("term") term: String,
        @Query("latitude") latitude: Double,
        @Query("longitude") longitude: Double
    ): Response
}