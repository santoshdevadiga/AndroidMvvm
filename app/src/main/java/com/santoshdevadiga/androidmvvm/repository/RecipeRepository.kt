package com.santoshdevadiga.androidmvvm.repository

import com.santoshdevadiga.androidmvvm.api.RecipesAPI
import com.santoshdevadiga.androidmvvm.model.RecipeLists
import com.santoshdevadiga.androidmvvm.utils.NetworkResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import org.json.JSONObject
import javax.inject.Inject

class RecipeRepository @Inject constructor(private val receipApi:RecipesAPI) {

    private val _recipeStateFlow = MutableStateFlow<NetworkResult<RecipeLists>>(NetworkResult.Loading())
    val recipeStateFlow: StateFlow<NetworkResult<RecipeLists>> = _recipeStateFlow

    suspend fun getRecipeList() {
        _recipeStateFlow.emit(NetworkResult.Loading())
        val response = receipApi.getRecipes()
        if (response.isSuccessful && response.body() != null) {
            _recipeStateFlow.emit(NetworkResult.Success(response.body()!!))
        } else if (response.errorBody() != null) {
            val errorObj = JSONObject(response.errorBody()!!.charStream().readText())
            _recipeStateFlow.emit(NetworkResult.Error(errorObj.getString("message")))
        } else {
            _recipeStateFlow.emit(NetworkResult.Error("Something Went Wrong"))
        }
    }
}