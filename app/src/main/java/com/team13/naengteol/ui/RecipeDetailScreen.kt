
package com.team13.naengteol.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.team13.naengteol.data.RecipeRepository
import com.team13.naengteol.data.RecipeDto

@Composable
fun RecipeDetailScreen(
    recipeId: Int,
    selected: Set<String>,
    onBack: () -> Unit
) {
    val recipe = remember(recipeId) {
        RecipeRepository.findRecipe(recipeId)
    }

    Column(
        Modifier
            .fillMaxSize()
            .padding(24.dp)
            .verticalScroll(rememberScrollState())
    ) {
        TextButton(onClick = onBack) {
            Text("← 뒤로")
        }

        if (recipe == null) {
            Text("레시피를 찾을 수 없어요.")
            return@Column
        }

        Text(
            recipe.name,
            style = MaterialTheme.typography.headlineSmall
        )

        Spacer(Modifier.height(16.dp))

        Text(
            "조리 방법: ${recipe.cookingMethod}",
            style = MaterialTheme.typography.bodyMedium
        )

        Text(
            "카테고리: ${recipe.category}",
            style = MaterialTheme.typography.bodyMedium
        )

        Spacer(Modifier.height(20.dp))

        Text(
            "필요한 재료",
            style = MaterialTheme.typography.titleMedium
        )

        Spacer(Modifier.height(8.dp))

        Text(recipe.ingredients)

        Spacer(Modifier.height(20.dp))

        Text(
            "조리 순서",
            style = MaterialTheme.typography.titleMedium
        )

        Spacer(Modifier.height(8.dp))

        recipe.instructions.forEachIndexed { index, step ->
            Text("${index + 1}. $step")
            Spacer(Modifier.height(12.dp))
        }

        Spacer(Modifier.height(20.dp))

        Text("칼로리: ${recipe.calories} kcal")
    }
}
