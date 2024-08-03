package com.app.tahaluf.business.university.data.main.datasource.remote

import com.app.tahaluf.business.university.data.entity.UniversityEntity
import retrofit2.http.GET
import retrofit2.http.Query

interface UniversityApiService {

    //http://universities.hipolabs.com/search?country=United%20Arab%20Emirate
    @GET("search")
    suspend fun universityList(@Query("country") country: String): List<UniversityEntity>

}
