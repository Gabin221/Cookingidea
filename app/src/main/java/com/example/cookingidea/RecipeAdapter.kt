package com.example.cookingidea

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecipeAdapter : RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder>() {

    private val recipes = mutableListOf<Recipe>()

    fun setRecipes(newRecipes: List<Recipe>?) {
        recipes.clear()
        if (!newRecipes.isNullOrEmpty()) {
            recipes.addAll(newRecipes)
        }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recipe_item, parent, false)
        return RecipeViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        val recipe = recipes[position]
        holder.bind(recipe)
    }

    override fun getItemCount(): Int = recipes.size

    inner class RecipeViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val titleTextView: TextView = view.findViewById(R.id.recipeTitle)
        private val linkTextView: TextView = view.findViewById(R.id.recipeUrl)

        fun bind(recipe: Recipe) {
            titleTextView.text = recipe.title
            linkTextView.text = recipe.sourceUrl
            linkTextView.setOnClickListener {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(recipe.sourceUrl))
                itemView.context.startActivity(intent)
            }
        }
    }
}
