
package com.team13.naengteol.data

object RecipeRepository {

    private var cachedRecipes: List<RecipeDto> = emptyList()

    suspend fun getRecipes(): List<RecipeDto> {
        if (cachedRecipes.isEmpty()) {
            cachedRecipes = try {
                val apiRecipes = RecipeApi.getRecipes()

                if (apiRecipes.isNotEmpty()) {
                    apiRecipes
                } else {
                    getLocalRecipes()
                }
            } catch (e: Exception) {
                getLocalRecipes()
            }
        }

        return cachedRecipes
    }

    fun findRecipe(id: Int): RecipeDto? {
        return cachedRecipes.find { it.id == id }
    }

    private fun getLocalRecipes(): List<RecipeDto> {
        return RECIPES.map { recipe ->
            RecipeDto(
                id = recipe.id,
                name = recipe.name,
                cookingMethod = "",
                category = "",
                ingredients = recipe.ingredients.joinToString(", ") {
                    "${it.name} ${it.amount}"
                },
                imageUrl = "",
                instructions = recipe.steps,
                instructionImages = emptyList(),
                calories = "",
                nutritionTip = ""
            )
        }
    }
}

