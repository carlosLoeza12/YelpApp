package com.example.yeplapp.repository
import com.example.yeplapp.data.model.Response

interface BusinessRepository {
    suspend fun getBusinessList(term: String, latitude: Double, longitude: Double): Response
}