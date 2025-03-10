package com.example.cookingidea

import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cookingidea.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: RecipeAdapter
    private val apiKey = "use/your/api/key"

    lateinit var resultats: TextView
    lateinit var linearLayoutResultats: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        resultats = binding.root.findViewById(R.id.resultats)
        linearLayoutResultats = binding.root.findViewById(R.id.linearLayoutResultats)

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = RecipeAdapter()
        binding.recyclerView.adapter = adapter

        binding.button.setOnClickListener {
            val diet = binding.editTextDiet.text.toString()
            val ingredients = binding.editTextIngredients.text.toString()
            val timeFrame = binding.editTextTimeFrame.text.toString()
            getRecipes(diet, ingredients, timeFrame)
        }
    }

    private fun getRecipes(diet: String, ingredients: String, timeFrame: String) {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.spoonacular.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val spoonacularApi = retrofit.create(SpoonacularApi::class.java)

        spoonacularApi.getRecipes(diet, ingredients, timeFrame, apiKey)
            .enqueue(object : Callback<MealResponse> {
                override fun onResponse(call: Call<MealResponse>, response: Response<MealResponse>) {
                    if (response.isSuccessful) {
                        val mealResponse = response
                        if (mealResponse != null) {
                            adapter.setRecipes(mealResponse.body()?.meals)
                            resultats.visibility = View.VISIBLE
                            linearLayoutResultats.visibility = View.VISIBLE
                        } else {
                            Toast.makeText(this@MainActivity, "No recipes found", Toast.LENGTH_SHORT).show()
                        }
                    } else {
                        Toast.makeText(this@MainActivity, "Failed to fetch recipes", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<MealResponse>, t: Throwable) {
                    Toast.makeText(this@MainActivity, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
                }
            })
    }

}
