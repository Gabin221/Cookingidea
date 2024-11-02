package com.example.cookingidea

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface SpoonacularApi {

    @GET("recipes/findByIngredients")
    fun getRecipes(
        @Query("ingredients") ingredients: String,
        @Query("number") number: Int,
        @Query("apiKey") apiKey: String
    ): Call<List<Recipe>>
}
