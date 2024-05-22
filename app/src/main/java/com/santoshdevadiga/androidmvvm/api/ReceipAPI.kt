package com.santoshdevadiga.androidmvvm.api

import com.santoshdevadiga.androidmvvm.model.Recipe
import com.santoshdevadiga.androidmvvm.model.RecipeLists
import retrofit2.Response
import retrofit2.http.GET

interface RecipesAPI {
    @GET("/recipes")
    suspend fun getRecipes():Response<RecipeLists>
}