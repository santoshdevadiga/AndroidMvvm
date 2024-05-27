package com.santoshdevadiga.androidmvvm.viewmodels

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
class RecipeViewmodel @Inject constructor(private val recipRepository: RecipeRepository) :ViewModel() {
    val receip:StateFlow<NetworkResult<RecipeLists>>
        get() = recipRepository.receipStateFlow

    init {
        viewModelScope.launch {
            recipRepository.getReceipList()
        }
    }

}