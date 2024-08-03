package com.app.tahaluf.business.university.domain.main.usecase

import com.app.tahaluf.business.university.domain.main.repository.UniversityRepository
import com.app.tahaluf.business.university.domain.model.University
import javax.inject.Inject

class GetUniversityDetailUseCase @Inject constructor(
    private val repository: UniversityRepository
) : UseCase.SuspendingParameterized<String, Result<University>> {
    override suspend fun invoke(param: String): Result<University> {
        return repository.universityDetail(name = param)
    }
}
