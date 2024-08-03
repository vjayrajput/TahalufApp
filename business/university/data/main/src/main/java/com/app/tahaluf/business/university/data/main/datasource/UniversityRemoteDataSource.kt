package com.app.tahaluf.business.university.data.main.datasource

import com.app.tahaluf.business.university.data.entity.UniversityEntity

interface UniversityRemoteDataSource {

    suspend fun universityList(country: String): Result<List<UniversityEntity>>
}
