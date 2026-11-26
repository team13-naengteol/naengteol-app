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

fun recommendApi(
    selected: Set<String>,
    recipes: List<RecipeDto>
): List<ApiRecommendResult> {

    val selectedNormalized = selected.map { item ->
        normalizeIngredient(item)
    }

    return recipes.mapNotNull { recipe ->

        val ingredientText = recipe.ingredients
        val owned = selected.filter { ingredient ->
            val normalized = normalizeIngredient(ingredient)

            normalized.isNotBlank() &&
                    ingredientText.contains(normalized)
        }

        val missing = ingredientText
            .split(Regex("[,\\n·]+"))
            .map { part -> part.trim() }
            .filter { part -> part.isNotBlank() }
            .filterNot { part ->
                API_BASIC_SEASONINGS.any { seasoning -> part.contains(seasoning) } ||
                        selectedNormalized.any { selectedItem ->
                            part.contains(selectedItem)
                        }
            }
            .distinct()
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
        compareByDescending<ApiRecommendResult> { result -> result.owned.size }
            .thenBy { result -> result.missing.size }
    )
}
