package com.app.tahaluf.business.university.data.main.mapper

import com.app.tahaluf.business.university.data.entity.UniversityEntity
import com.app.tahaluf.business.university.domain.model.University
import javax.inject.Inject

class UniversityMapper @Inject constructor() : Mapper<UniversityEntity, University> {

    override fun mapTo(type: UniversityEntity?): University {
        return University(
            country = type?.country.orEmpty(),
            name = type?.name.orEmpty(),
            stateProvince = type?.stateProvince.orEmpty(),
            alphaTwoCode = type?.alphaTwoCode.orEmpty(),
            webPages = type?.webPages ?: emptyList(),
            domains = type?.domains ?: emptyList(),
        )
    }
}
