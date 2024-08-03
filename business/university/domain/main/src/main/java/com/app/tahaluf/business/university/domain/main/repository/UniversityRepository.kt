package com.app.tahaluf.business.university.domain.main.repository

import com.app.tahaluf.business.university.domain.model.University

interface UniversityRepository {

    suspend fun universityList(country: String): Result<List<University>>

    suspend fun universityDetail(name: String): Result<University>
}
