package com.example.yeplapp.repository

import com.example.yeplapp.data.model.Response
import com.example.yeplapp.data.remote.RemoteBusinessDataSource
import javax.inject.Inject

class BusinessRepositoryImpl @Inject constructor(private val remoteBusinessDataSource: RemoteBusinessDataSource): BusinessRepository {

    override suspend fun getBusinessList(term: String, latitude: Double, longitude: Double): Response {
         return remoteBusinessDataSource.getBusinessList(
            term = term,
            latitude = latitude,
            longitude = longitude)
    }

}