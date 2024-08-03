package com.app.tahaluf.business.university.di

import com.app.tahaluf.business.university.data.di.UniversityDataModule
import com.app.tahaluf.business.university.domain.di.UniversityDomainModule
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module(includes = [UniversityDomainModule::class, UniversityDataModule::class])
@InstallIn(SingletonComponent::class)
object UniversityModule {
    //DO NOTHING
}
