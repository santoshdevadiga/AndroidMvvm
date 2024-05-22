package com.santoshdevadiga.androidmvvm.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.santoshdevadiga.androidmvvm.model.Recipe
import com.santoshdevadiga.androidmvvm.model.RecipeLists
import com.santoshdevadiga.androidmvvm.repository.ReceipRepository
import com.santoshdevadiga.androidmvvm.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ReceipViewmodel @Inject constructor(private val recipRepository: ReceipRepository) :ViewModel() {
    val receip:StateFlow<NetworkResult<RecipeLists>>
        get() = recipRepository.receipStateFlow

    init {
        viewModelScope.launch {
            recipRepository.getReceipList()
        }
    }

}