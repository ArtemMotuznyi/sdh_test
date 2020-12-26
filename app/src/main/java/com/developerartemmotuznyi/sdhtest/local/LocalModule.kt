package com.developerartemmotuznyi.sdhtest.local

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.createDataStore
import com.developerartemmotuznyi.sdhtest.MedicinePreferences
import com.developerartemmotuznyi.sdhtest.local.datastore.serializer.MedicinePreferencesSerializer
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
object LocalModule {

	@Singleton
	@Provides
	fun provideProtoDataStore(@ApplicationContext applicationContext: Context): DataStore<MedicinePreferences> =
			applicationContext.createDataStore(
					fileName = "profile_prefs.pb",
					serializer = MedicinePreferencesSerializer
			)
}