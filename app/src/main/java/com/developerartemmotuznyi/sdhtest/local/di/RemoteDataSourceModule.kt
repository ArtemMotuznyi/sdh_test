package com.developerartemmotuznyi.sdhtest.local.di

import com.developerartemmotuznyi.sdhtest.local.datastore.source.DefaultMedicineLocalDataSource
import com.developerartemmotuznyi.sdhtest.local.datastore.source.MedicineLocalDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
abstract class LocalDataSourceModule {

	@Binds
	abstract fun bindMedicinesLocalDataSource(dataSource: DefaultMedicineLocalDataSource): MedicineLocalDataSource

}