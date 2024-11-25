package com.example.project1.data.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [ServiceEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun serviceDao(): ServiceDao
}
