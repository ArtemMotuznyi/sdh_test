package com.developerartemmotuznyi.sdhtest.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.developerartemmotuznyi.sdhtest.local.model.MedicineEntity

@Database(entities = arrayOf(
		MedicineEntity::class,

), version = 1)
abstract class MedicineDataBase : RoomDatabase() {
}