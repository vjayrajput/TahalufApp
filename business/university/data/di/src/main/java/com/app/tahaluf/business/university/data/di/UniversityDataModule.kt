package com.app.tahaluf.business.university.data.di

import android.app.Application
import androidx.room.Room
import com.app.tahaluf.business.university.data.main.BuildConfig
import com.app.tahaluf.business.university.data.main.datasource.UniversityLocalDataSource
import com.app.tahaluf.business.university.data.main.datasource.UniversityRemoteDataSource
import com.app.tahaluf.business.university.data.main.datasource.local.UniversityLocalDataSourceImpl
import com.app.tahaluf.business.university.data.main.datasource.local.dao.UniversityDao
import com.app.tahaluf.business.university.data.main.datasource.local.database.UniversityAppDatabase
import com.app.tahaluf.business.university.data.main.datasource.remote.UniversityApiService
import com.app.tahaluf.business.university.data.main.datasource.remote.UniversityRemoteDataSourceImpl
import com.app.tahaluf.business.university.data.main.mapper.UniversityMapper
import com.app.tahaluf.business.university.data.main.repository.UniversityRepositoryImpl
import com.app.tahaluf.business.university.domain.main.repository.UniversityRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UniversityDataModule {

    private const val DEFAULT_TIMEOUT = 60L

    @Singleton
    @Provides
    fun provideBaseURL(): String {
        return BuildConfig.BASE_URL
    }

    @Singleton
    @Provides
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }


    @Singleton
    @Provides
    fun provideOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        val okHttpClient = OkHttpClient().newBuilder()

        okHttpClient.callTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
        okHttpClient.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
        okHttpClient.readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
        okHttpClient.writeTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
        okHttpClient.addInterceptor(loggingInterceptor)
        okHttpClient.build()
        return okHttpClient.build()
    }


    @Singleton
    @Provides
    fun provideConverterFactory(): Converter.Factory {
        return GsonConverterFactory.create()
    }


    @Singleton
    @Provides
    fun provideRetrofitClient(
        baseUrl: String,
        okHttpClient: OkHttpClient,
        converterFactory: Converter.Factory
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(converterFactory)
            .build()
    }


    @Singleton
    @Provides
    fun provideUniversityApiService(retrofit: Retrofit): UniversityApiService {
        return retrofit.create(UniversityApiService::class.java)
    }

    @Singleton
    @Provides
    fun provideUniversityRepository(
        localDataSource: UniversityLocalDataSource,
        remoteDataSource: UniversityRemoteDataSource,
        universityMapper: UniversityMapper,
    ): UniversityRepository {
        return UniversityRepositoryImpl(
            localDataSource = localDataSource,
            remoteDataSource = remoteDataSource,
            universityMapper = universityMapper,
        )
    }

    @Provides
    @Singleton
    fun provideUniversityAppDatabase(app: Application): UniversityAppDatabase {
        return Room.databaseBuilder(
            app,
            UniversityAppDatabase::class.java,
            UniversityAppDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    fun provideUniversityDao(database: UniversityAppDatabase): UniversityDao =
        database.universityDao()

    @Singleton
    @Provides
    fun provideUniversityLocalDataSource(
        universityDao: UniversityDao,
    ): UniversityLocalDataSource {
        return UniversityLocalDataSourceImpl(
            universityDao = universityDao,
        )
    }

    @Singleton
    @Provides
    fun provideUniversityRemoteDataSource(
        apiService: UniversityApiService,
    ): UniversityRemoteDataSource {
        return UniversityRemoteDataSourceImpl(
            apiService = apiService,
        )
    }
}
