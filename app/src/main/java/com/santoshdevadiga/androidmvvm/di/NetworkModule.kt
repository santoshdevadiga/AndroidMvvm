package com.santoshdevadiga.androidmvvm.di

import com.santoshdevadiga.androidmvvm.api.RecipesAPI
import com.santoshdevadiga.androidmvvm.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {

    @Singleton
    @Provides
    fun providesRetrofit(): Retrofit.Builder {
        return Retrofit.Builder().baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
    }

    @Singleton
    @Provides
    fun providesRecipeAPI(retrofitBuilder: Retrofit.Builder): RecipesAPI {
        return retrofitBuilder.build().create(RecipesAPI::class.java)
    }

}