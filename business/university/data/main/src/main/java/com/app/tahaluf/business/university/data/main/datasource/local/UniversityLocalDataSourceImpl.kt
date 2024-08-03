package com.app.tahaluf.business.university.data.main.datasource.local

import com.app.tahaluf.business.university.data.entity.UniversityEntity
import com.app.tahaluf.business.university.data.main.datasource.UniversityLocalDataSource
import com.app.tahaluf.business.university.data.main.datasource.local.dao.UniversityDao
import javax.inject.Inject


class UniversityLocalDataSourceImpl @Inject constructor(
    private val universityDao: UniversityDao,
) : UniversityLocalDataSource {

    override suspend fun universityList(country: String): List<UniversityEntity> {
        return universityDao.getUniversities(country)
    }

    override suspend fun insertAllUniversities(data: List<UniversityEntity>) {
        universityDao.insertUniversities(data)
    }

    override suspend fun universityDetail(name: String): UniversityEntity? {
        return universityDao.getUniversityByName(name)
    }

    override suspend fun deleteUniversitiesByCountry(country: String) {
        universityDao.deleteUniversitiesByCountry(country)
    }

}

