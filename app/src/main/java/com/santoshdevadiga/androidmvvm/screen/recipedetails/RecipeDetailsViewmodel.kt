package com.santoshdevadiga.androidmvvm.screen.recipedetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.santoshdevadiga.androidmvvm.model.RecipeLists
import com.santoshdevadiga.androidmvvm.repository.RecipeRepository
import com.santoshdevadiga.androidmvvm.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipeDetailsViewmodel @Inject constructor(private val recipeRepository: RecipeRepository) :ViewModel() {


}