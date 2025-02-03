package com.rach.firstnodejs.dagger

import com.rach.firstnodejs.api.ApiService
import com.rach.firstnodejs.api.UserDataSource
import com.rach.firstnodejs.api.UserRemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRetrofit():Retrofit{
        return Retrofit.Builder()
            .baseUrl("")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit):ApiService{
        return retrofit.create(ApiService::class.java)
    }

    @Singleton
    @Provides
    fun provideUserDataSource(apiService: ApiService):UserDataSource{
        return UserRemoteDataSource(apiService)
    }

    
}
