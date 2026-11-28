
package com.team13.naengteol.logic

import com.team13.naengteol.data.RecipeDto

data class ApiRecommendResult(
    val recipe: RecipeDto,
    val owned: List<String>,
    val missing: List<String>
)

private val API_BASIC_SEASONINGS = setOf(
    "소금", "식용유", "간장", "설탕", "후추",
    "참기름", "깨", "물"
)

private fun normalizeIngredient(value: String): String {
    return when (value.trim()) {
        "계란" -> "달걀"
        "대파" -> "파"
        else -> value.trim()
    }
}

// Convert the API ingredient text into individual ingredient names.
private fun extractIngredients(text: String): List<String> {
    return text
        .split(Regex("[,\\n·]+"))
        .map { part ->
            part.trim()
                .replace(Regex("\\s+.*$"), "")
        }
        .filter { it.isNotBlank() }
        .map { normalizeIngredient(it) }
        .distinct()
}

fun recommendApi(
    selected: Set<String>,
    recipes: List<RecipeDto>
): List<ApiRecommendResult> {

    val selectedNormalized = selected
        .map { normalizeIngredient(it) }
        .toSet()

    return recipes.mapNotNull { recipe ->

        val recipeIngredients = extractIngredients(recipe.ingredients)
        val recipeIngredientSet = recipeIngredients.toSet()

        val owned = selected
            .filter { ingredient ->
                normalizeIngredient(ingredient) in recipeIngredientSet
            }

        val missing = recipeIngredients
            .filterNot { ingredient ->
                ingredient in API_BASIC_SEASONINGS ||
                        ingredient in selectedNormalized
            }
            .take(8)

        if (owned.isEmpty()) {
            null
        } else {
            ApiRecommendResult(
                recipe = recipe,
                owned = owned,
                missing = missing
            )
        }
    }.sortedWith(
        compareByDescending<ApiRecommendResult> { it.owned.size }
            .thenBy { it.missing.size }
    )
}