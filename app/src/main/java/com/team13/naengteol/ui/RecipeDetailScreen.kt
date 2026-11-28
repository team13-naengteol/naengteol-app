package com.team13.naengteol.ui

// 담당: 빌구대이 (4번) — 레시피 상세 화면
// 지금은 작동 확인용 최소 버전. 디자인은 자유롭게 바꾸되 함수 모양은 유지하기

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.team13.naengteol.data.findRecipe
import com.team13.naengteol.logic.BASIC_SEASONINGS

@Composable
fun RecipeDetailScreen(
    recipeId: Int,
    selected: Set<String>,
    onBack: () -> Unit
) {
    val recipe = findRecipe(recipeId)

    Column(Modifier.fillMaxSize().padding(24.dp).verticalScroll(rememberScrollState())) {
        TextButton(onClick = onBack) { Text("← 뒤로") }

        if (recipe == null) {
            Text("레시피를 찾을 수 없어요.")
            return@Column
        }

        Text(recipe.name, style = MaterialTheme.typography.headlineSmall)
        Spacer(Modifier.height(16.dp))

        Text("필요한 재료", style = MaterialTheme.typography.titleMedium)
        recipe.ingredients.forEach { ing ->
            val lacking = ing.name !in selected && ing.name !in BASIC_SEASONINGS
            Text(
                text = "${ing.name}  ${ing.amount}" + if (lacking) "  (부족)" else "",
                color = if (lacking) MaterialTheme.colorScheme.error else Color.Unspecified
            )
        }
        Spacer(Modifier.height(16.dp))

        Text("조리 순서", style = MaterialTheme.typography.titleMedium)
        recipe.steps.forEachIndexed { i, step ->
            Text("${i + 1}. $step")
        }
    }
}
