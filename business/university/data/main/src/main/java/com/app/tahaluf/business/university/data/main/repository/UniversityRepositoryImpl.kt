package com.app.tahaluf.business.university.data.main.repository

import com.app.tahaluf.business.university.data.main.datasource.UniversityLocalDataSource
import com.app.tahaluf.business.university.data.main.datasource.UniversityRemoteDataSource
import com.app.tahaluf.business.university.data.main.mapper.UniversityMapper
import com.app.tahaluf.business.university.domain.main.repository.UniversityRepository
import com.app.tahaluf.business.university.domain.model.University
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UniversityRepositoryImpl @Inject constructor(
    private val localDataSource: UniversityLocalDataSource,
    private val remoteDataSource: UniversityRemoteDataSource,
    private val universityMapper: UniversityMapper,
) : UniversityRepository {

    override suspend fun universityList(country: String): Result<List<University>> {
        return withContext(Dispatchers.IO) {
            try {
                val result = remoteDataSource.universityList(country)
                if (result.isSuccess) {
                    val data = result.getOrNull()
                    if (data != null) {
                        localDataSource.deleteUniversitiesByCountry(country)
                        localDataSource.insertAllUniversities(data)
                        Result.success(data.map { universityMapper.mapTo(it) })
                    } else {
                        Result.success(emptyList())
                    }
                } else {
                    localFromCache(country)
                }
            } catch (e: Exception) {
                e.printStackTrace()
                localFromCache(country)
            }
        }
    }

    private suspend fun localFromCache(country: String): Result<List<University>> {
        val dbData = localDataSource.universityList(country)
        return if (dbData.isEmpty()) {
            Result.failure(Exception("Something went wrong."))
        } else {
            Result.success(dbData.map {
                universityMapper.mapTo(it)
            })
        }
    }

    override suspend fun universityDetail(name: String): Result<University> =
        withContext(Dispatchers.IO) {
            localDataSource.universityDetail(name)?.let {
                Result.success(universityMapper.mapTo(it))
            } ?: Result.failure(Exception("University not found"))
        }
}

