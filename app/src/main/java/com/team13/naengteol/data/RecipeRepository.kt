
package com.team13.naengteol.data

object RecipeRepository {

    private var cachedRecipes: List<RecipeDto> = emptyList()

    suspend fun getRecipes(): List<RecipeDto> {
        if (cachedRecipes.isEmpty()) {
            cachedRecipes = RecipeApi.getRecipes()
        }

        return cachedRecipes
    }

    fun findRecipe(id: Int): RecipeDto? {
        return cachedRecipes.find { it.id == id }
    }
}
