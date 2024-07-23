package com.santoshdevadiga.androidmvvm.screen.auth.login

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel


@Composable
fun LoginScreen(modifier: Modifier = Modifier) {
    val loginViewmodel: LoginViewmodel = viewModel()

}