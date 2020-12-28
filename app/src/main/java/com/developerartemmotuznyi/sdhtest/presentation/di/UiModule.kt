package com.developerartemmotuznyi.sdhtest.presentation.di

import android.content.Context
import com.developerartemmotuznyi.networkconnectivitymanager.NetworkConnectivityProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object UiModule {

    @Singleton
    @Provides
    fun provideNetworkConnectivityProvider(@ApplicationContext context: Context) =
        NetworkConnectivityProvider.createProvider(context)

}