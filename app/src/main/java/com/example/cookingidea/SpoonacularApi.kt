package com.example.cookingidea

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface SpoonacularApi {
    @GET("mealplanner/generate")
    fun getRecipes(
        @Query("diet") diet: String,
        @Query("exclude") ingredients: String,
        @Query("timeFrame") timeFrame: String,
        @Query("apiKey") apiKey: String
    ): Call<MealResponse>

}
