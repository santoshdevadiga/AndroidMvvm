package com.santoshdevadiga.androidmvvm

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.santoshdevadiga.androidmvvm.api.RecipesAPI
import com.santoshdevadiga.androidmvvm.screen.ReceipScreen
import com.santoshdevadiga.androidmvvm.ui.theme.AndroidMvvmTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var receipAPI:RecipesAPI

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            AndroidMvvmTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ReceipScreen(
                        modifier = Modifier.padding(innerPadding)
                    )

                }
            }
        }

    }
}



@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AndroidMvvmTheme {
        ReceipScreen(modifier=Modifier)
    }
}