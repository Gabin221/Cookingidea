package com.example.cookingidea

data class MealResponse(
    val meals: List<Recipe>?
)

data class Recipe(
    val id: Int,
    val title: String,
    val sourceUrl: String
)

