package com.diehard04.exoplayer.data.api

import com.diehard04.exoplayer.data.model.Resource
import com.diehard04.exoplayer.data.model.VideoResponse
import retrofit2.Response
import retrofit2.http.GET

// ApiService.kt
interface ApiService {
    @GET("5946718a-02c2-42a0-9c80-42e1f5758eac")
    suspend fun getVideoUrl(): Response<VideoResponse>
}