package com.diehard04.exoplayer.di

import com.diehard04.exoplayer.data.api.ApiService
import com.diehard04.exoplayer.data.repository.VideoRepository
import com.diehard04.exoplayer.utils.PublicValues
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideApiService():ApiService = Retrofit.Builder()
        .baseUrl(PublicValues.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ApiService::class.java)



    @Provides
    fun provideRepository(apiService: ApiService): VideoRepository = VideoRepository(apiService)
}