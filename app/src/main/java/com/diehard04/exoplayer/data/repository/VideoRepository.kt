package com.diehard04.exoplayer.data.repository

import com.diehard04.exoplayer.data.api.ApiService
import com.diehard04.exoplayer.data.model.Resource
import javax.inject.Inject


// VideoRepository.kt
class VideoRepository @Inject constructor(private val apiService: ApiService) {
    suspend fun fetchVideo(): Resource<String> {
        return try {
            val response = apiService.getVideoUrl()
            if (response.isSuccessful) {
                response.body()?.url?.let {
                    Resource.Success(it)
                } ?: Resource.Error("Empty URL")
            } else {
                Resource.Error(response.message())
            }
        } catch (e: Exception) {
            Resource.Error(e.localizedMessage ?: "Unknown error")
        }
    }
}