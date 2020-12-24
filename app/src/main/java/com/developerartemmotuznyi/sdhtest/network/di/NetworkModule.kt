package com.developerartemmotuznyi.sdhtest.network.di

import com.developerartemmotuznyi.sdhtest.BuildConfig
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import javax.inject.Singleton


@InstallIn(ApplicationComponent::class)
@Module
object NetworkModule {

    @OptIn(ExperimentalSerializationApi::class)
    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .addConverterFactory(Json{
                ignoreUnknownKeys = true
            }.asConverterFactory("application/json".toMediaType()))
            .client(okHttpClient)
            .baseUrl(BuildConfig.BASE_API)
            .build()

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build()

}
