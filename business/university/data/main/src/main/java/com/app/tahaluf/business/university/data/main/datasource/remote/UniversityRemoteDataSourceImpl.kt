package com.app.tahaluf.business.university.data.main.datasource.remote

import com.app.tahaluf.business.university.data.entity.UniversityEntity
import com.app.tahaluf.business.university.data.main.datasource.UniversityRemoteDataSource
import com.app.tahaluf.common.network.safeApiCall
import javax.inject.Inject

class UniversityRemoteDataSourceImpl @Inject constructor(
    private val apiService: UniversityApiService,
) : UniversityRemoteDataSource {

    override suspend fun universityList(country: String): Result<List<UniversityEntity>> {
        return safeApiCall { apiService.universityList(country) }
    }

}
