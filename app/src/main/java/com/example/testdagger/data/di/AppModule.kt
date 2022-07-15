package com.example.testdagger.data.di

import com.example.testdagger.domain.ApiService
import com.example.testdagger.domain.instance.RetrofitInstance
import com.example.testdagger.presentation.MainPresenter
import com.example.testdagger.presentation.repository.MainRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideApiService(): ApiService {
        return RetrofitInstance.getRetrofitInstance().create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideMainRepository(apiService: ApiService): MainRepository {
        return MainRepository(apiService)
    }

    @Provides
    @Singleton
    fun provideMainPresenter(mainRepository: MainRepository): MainPresenter {
        return MainPresenter(mainRepository)
    }
}