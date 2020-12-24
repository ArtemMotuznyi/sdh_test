package com.developerartemmotuznyi.sdhtest.network.di

import com.developerartemmotuznyi.sdhtest.network.api.MedicineApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import retrofit2.Retrofit

@InstallIn(ApplicationComponent::class)
@Module
object ApiModule {

    @Provides
    fun provideMedicineApi(retrofit: Retrofit): MedicineApi = retrofit.create(
        MedicineApi::class.java
    )

}