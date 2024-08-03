package com.app.tahaluf.business.university.data.main.datasource.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.app.tahaluf.business.university.data.entity.UniversityEntity

@Dao
interface UniversityDao {
    @Query("SELECT * FROM university_entity WHERE country = :country")
    suspend fun getUniversities(country: String): List<UniversityEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUniversities(universities: List<UniversityEntity>)

    @Query("DELETE FROM university_entity WHERE country = :country")
    suspend fun deleteUniversitiesByCountry(country: String)

    @Query("SELECT * FROM university_entity WHERE name = :name")
    suspend fun getUniversityByName(name: String): UniversityEntity?
}
