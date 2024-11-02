package com.example.cookingidea

import android.os.Bundle
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = RecipeAdapter()
        binding.recyclerView.adapter = adapter

        binding.button.setOnClickListener {
            val numberOfRecipes = binding.editTextNumber.text.toString().toIntOrNull() ?: 0
            val allergies = binding.editTextAllergies.text.toString()
            val ingredients = binding.editTextIngredients.text.toString()

            getRecipes(ingredients, numberOfRecipes)
        }
    }

    private fun getRecipes(ingredients: String, number: Int) {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.spoonacular.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val spoonacularApi = retrofit.create(SpoonacularApi::class.java)

        spoonacularApi.getRecipes(ingredients, number, apiKey).enqueue(object : Callback<List<Recipe>> {
            override fun onResponse(call: Call<List<Recipe>>, response: Response<List<Recipe>>) {
                if (response.isSuccessful && response.body() != null) {
                    adapter.setRecipes(response.body()!!)
                } else {
                    Toast.makeText(this@MainActivity, "No recipes found", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<List<Recipe>>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
