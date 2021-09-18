package com.example.ecommerceapp.di

import android.content.Context
import com.example.ecommerceapp.network.ApiServices
import com.example.ecommerceapp.network.RemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideRemoteDataSource(): RemoteDataSource {
        return RemoteDataSource()
    }

    @Singleton
    @Provides
    fun provideApiServices(
        remoteDataSource: RemoteDataSource,
        @ApplicationContext context: Context
    ): ApiServices {
        return remoteDataSource.buildApi(ApiServices::class.java, context)
    }


}