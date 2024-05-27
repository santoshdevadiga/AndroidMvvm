package com.santoshdevadiga.androidmvvm.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Recipe(
    @SerialName("caloriesPerServing")
    val caloriesPerServing: Int, // 300
    @SerialName("cookTimeMinutes")
    val cookTimeMinutes: Int, // 15
    @SerialName("cuisine")
    val cuisine: String, // Italian
    @SerialName("difficulty")
    val difficulty: String, // Easy
    @SerialName("id")
    val id: Int, // 1
    @SerialName("image")
    val image: String,
    @SerialName("ingredients")
    val ingredients: List<String>,
    @SerialName("instructions")
    val instructions: List<String>,
    @SerialName("mealType")
    val mealType: List<String>,
    @SerialName("name")
    val name: String, // Classic Margherita Pizza
    @SerialName("prepTimeMinutes")
    val prepTimeMinutes: Int, // 20
    @SerialName("rating")
    val rating: Double, // 4.6
    @SerialName("reviewCount")
    val reviewCount: Int, // 3
    @SerialName("servings")
    val servings: Int, // 4
    @SerialName("tags")
    val tags: List<String>,
    @SerialName("userId")
    val userId: Int // 45
)