package com.app.tahaluf.business.university.data.main.datasource.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.app.tahaluf.business.university.data.entity.UniversityEntity
import com.app.tahaluf.business.university.data.main.datasource.local.dao.UniversityDao

@Database(entities = [UniversityEntity::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class UniversityAppDatabase : RoomDatabase() {


    abstract fun universityDao(): UniversityDao

    companion object {
        const val DATABASE_NAME = "university_db"
    }
}
