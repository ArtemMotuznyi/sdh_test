package com.developerartemmotuznyi.sdhtest.network.di

import com.developerartemmotuznyi.sdhtest.network.source.DefaultMedicineRemoteDataSource
import com.developerartemmotuznyi.sdhtest.network.source.MedicineRemoteDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@Module
@InstallIn(ApplicationComponent::class)
abstract class RemoteDataSourceModule {

    @Binds
    abstract fun bindMedicinesRemoteDataSource(dataSource: DefaultMedicineRemoteDataSource): MedicineRemoteDataSource

}