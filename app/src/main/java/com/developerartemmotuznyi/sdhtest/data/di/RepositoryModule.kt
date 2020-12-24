package com.developerartemmotuznyi.sdhtest.data.di

import com.developerartemmotuznyi.sdhtest.data.repository.DefaultMedicineRepository
import com.developerartemmotuznyi.sdhtest.data.repository.MedicineRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@Module
@InstallIn(ApplicationComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindMedicineRepository(repository: DefaultMedicineRepository): MedicineRepository

}