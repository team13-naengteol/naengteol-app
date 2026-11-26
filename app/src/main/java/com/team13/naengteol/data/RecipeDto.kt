package com.team13.naengteol.data

data class RecipeDto(
    val id: Int,
    val name: String,
    val cookingMethod: String,
    val category: String,
    val ingredients: String,
    val imageUrl: String,
    val instructions: List<String>,
    val instructionImages: List<String>,
    val calories: String,
    val nutritionTip: String
)
