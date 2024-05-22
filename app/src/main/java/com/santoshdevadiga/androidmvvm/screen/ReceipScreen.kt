package com.santoshdevadiga.androidmvvm.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.santoshdevadiga.androidmvvm.model.Recipe
import com.santoshdevadiga.androidmvvm.model.RecipeLists
import com.santoshdevadiga.androidmvvm.utils.NetworkResult
import com.santoshdevadiga.androidmvvm.viewmodels.ReceipViewmodel

@Composable
fun ReceipScreen(modifier: Modifier = Modifier) {
        val receipViewmodel:ReceipViewmodel = viewModel()
        val receip: State<NetworkResult<RecipeLists>> =receipViewmodel.receip.collectAsState()

        LazyVerticalGrid(
            columns = GridCells.Fixed(1),
            contentPadding = PaddingValues(8.dp),
            verticalArrangement = Arrangement.SpaceAround

        ) {
            receip.value.data?.let {
                items(it.recipes){
                    ReceipItem(it)
                }
            }
        }
}

@Composable
fun ReceipItem(receipItem:Recipe) {
    Text(text = receipItem.name)
}