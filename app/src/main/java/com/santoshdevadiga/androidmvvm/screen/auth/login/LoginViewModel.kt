package com.santoshdevadiga.androidmvvm.screen.auth.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.santoshdevadiga.androidmvvm.repository.RecipeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewmodel @Inject constructor(private val recipeRepository: RecipeRepository) :
    ViewModel() {

    init {
        viewModelScope.launch {

        }
    }

}