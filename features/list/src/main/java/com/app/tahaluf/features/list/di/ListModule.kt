package com.app.tahaluf.features.list.di

import com.app.tahaluf.business.university.domain.main.usecase.GetUniversityListUseCase
import com.app.tahaluf.features.list.ListViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class ListModule {

    @Provides
    fun provideListViewModel(
        getUniversityListUseCase: GetUniversityListUseCase,
    ): ListViewModel {
        return ListViewModel(
            getUniversityListUseCase,
        )
    }
}
