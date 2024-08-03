package com.app.tahaluf.business.university.data.main.datasource

import com.app.tahaluf.business.university.data.entity.UniversityEntity

interface UniversityLocalDataSource {

    suspend fun universityList(country: String): List<UniversityEntity>

    suspend fun insertAllUniversities(data: List<UniversityEntity>)

    suspend fun universityDetail(name: String): UniversityEntity?

    suspend fun deleteUniversitiesByCountry(country: String)
}
