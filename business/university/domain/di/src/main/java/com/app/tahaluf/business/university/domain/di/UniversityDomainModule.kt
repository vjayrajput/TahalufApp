package com.app.tahaluf.business.university.domain.di

import com.app.tahaluf.business.university.domain.main.repository.UniversityRepository
import com.app.tahaluf.business.university.domain.main.usecase.GetUniversityDetailUseCase
import com.app.tahaluf.business.university.domain.main.usecase.GetUniversityListUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object UniversityDomainModule {

    @Provides
    fun provideGetUniversityListUseCase(repository: UniversityRepository) =
        GetUniversityListUseCase(repository)

    @Provides
    fun provideGetUniversityDetailUseCase(repository: UniversityRepository) =
        GetUniversityDetailUseCase(repository)
}
