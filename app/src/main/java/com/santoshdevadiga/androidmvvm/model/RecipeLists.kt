package com.santoshdevadiga.androidmvvm.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RecipeLists(
    @SerialName("limit")
    val limit: Int, // 30
    @SerialName("recipes")
    val recipes: List<Recipe>,
    @SerialName("skip")
    val skip: Int, // 0
    @SerialName("total")
    val total: Int // 50
)