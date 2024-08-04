package com.app.tahaluf.features.detail.di

import com.app.tahaluf.business.university.domain.main.usecase.GetUniversityDetailUseCase
import com.app.tahaluf.features.detail.DetailViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DetailModule {

    @Provides
    fun provideDetailViewModel(
        getUniversityDetailUseCase: GetUniversityDetailUseCase,
    ): DetailViewModel {
        return DetailViewModel(
            getUniversityDetailUseCase,
        )
    }
}