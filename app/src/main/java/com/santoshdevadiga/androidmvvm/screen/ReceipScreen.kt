package com.santoshdevadiga.androidmvvm.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.motionEventSpy
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
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
            receip.value.data?.let { recipeLists ->
                items(recipeLists.recipes){
                    ReceipItem(it)
                }
            }
        }
}

@Composable
fun ReceipItem(recipe: Recipe) {

    Card(
        modifier = Modifier
            .fillMaxWidth() // Optional: Set width
            .padding(16.dp), // Padding around content
        shape = RoundedCornerShape(8.dp), // Set corner radius
        elevation = CardDefaults.cardElevation(defaultElevation = 5.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth(),
            verticalArrangement =  Arrangement.SpaceEvenly
        ) {
            Text(
                text = recipe.name,
                style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 18.sp)
            )
            Spacer(Modifier.height(20.dp))
            Row(horizontalArrangement = Arrangement.Start) {
                AsyncImage(
                    model = recipe.image,
                    contentDescription = "Recipe Image",
                    modifier = Modifier
                        .width(120.dp)
                        .height(120.dp)
                        .border(1.dp, Color.Gray) // Use chaining for less indentation
                )
                Spacer(Modifier.width(20.dp))
                Column (modifier = Modifier.height(120.dp),verticalArrangement = Arrangement.SpaceAround, horizontalAlignment = Alignment.Start){
                    Text(text = "Meal Type", style = TextStyle(fontWeight = FontWeight.W900)) // Use string interpolation for readability
                    Text(text = "Cuisine", style = TextStyle(fontWeight = FontWeight.W900))
                    Text(text = "Cal Per Serving", style = TextStyle(fontWeight = FontWeight.W900))
                    Text(text = "Cook Time (Min)", style = TextStyle(fontWeight = FontWeight.W900))
                    Text(text = "Prep Time (Min)", style = TextStyle(fontWeight = FontWeight.W900))
                }
                Column(modifier = Modifier.height(120.dp),verticalArrangement = Arrangement.SpaceAround, horizontalAlignment = Alignment.Start) {
                    Text(text = ": ${recipe.mealType.first()}", style = TextStyle(fontWeight = FontWeight.W400))
                    Text(text = ": ${recipe.cuisine}", style = TextStyle(fontWeight = FontWeight.W400))
                    Text(text = ": ${recipe.caloriesPerServing}", style = TextStyle(fontWeight = FontWeight.W400))
                    Text(text = ": ${recipe.cookTimeMinutes}", style = TextStyle(fontWeight = FontWeight.W400))
                    Text(text = ": ${recipe.prepTimeMinutes}", style = TextStyle(fontWeight = FontWeight.W400))
                }
            }
        }
    }

}


@Preview(showBackground = true)
@Composable
fun PreviewReceipItem(){
    val  receiptItem=Recipe(
        id = 1,
        mealType = listOf("Dinner"),
        cuisine = "130",
        caloriesPerServing = 4,
        cookTimeMinutes = 10,
        prepTimeMinutes = 10,
        image = "https://cdn.dummyjson.com/recipe-images/1.webp",
        name = "Classic Margherita Pizza",
        reviewCount = 1,
        tags = emptyList(),
        rating = 1.0,
        userId = 32,
        servings = 1,
        difficulty = "easy",
        instructions = emptyList(),
        ingredients = emptyList()
    )
    ReceipItem(receiptItem)
}