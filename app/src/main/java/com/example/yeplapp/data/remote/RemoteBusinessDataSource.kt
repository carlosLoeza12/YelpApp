package com.example.yeplapp.data.remote

import com.example.yeplapp.data.model.Response
import com.example.yeplapp.repository.WebService
import javax.inject.Inject

class RemoteBusinessDataSource @Inject constructor(private val webService: WebService) {
    suspend fun getBusinessList(term: String, latitude: Double, longitude: Double): Response {
        return webService.getBusinesses(
            term = term,
            latitude = latitude,
            longitude = longitude,
            locale = "es_MX"
        )
    }
}