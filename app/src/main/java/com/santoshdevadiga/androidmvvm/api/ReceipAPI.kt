package com.santoshdevadiga.androidmvvm.api

import com.santoshdevadiga.androidmvvm.model.LoginRequest
import com.santoshdevadiga.androidmvvm.model.LoginResponse
import com.santoshdevadiga.androidmvvm.model.RecipeLists
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface RecipesAPI {

    @GET("/recipes")
    suspend fun getRecipes():Response<RecipeLists>

    @Headers("Content-Type:application/json; charset=UTF-8")
    @POST("/user/login")
    suspend fun userLogin(@Body payload:LoginRequest):Response<LoginResponse>
}

