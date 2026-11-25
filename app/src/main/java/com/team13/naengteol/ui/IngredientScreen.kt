package com.team13.naengteol.ui

// 담당: 심여진 (2번) — 재료 선택 화면

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.unit.dp
import com.team13.naengteol.data.ALL_INGREDIENTS
import com.team13.naengteol.ui.components.PillButton
import com.team13.naengteol.ui.components.YellowHeader

private val ingredientSymbols = mapOf(
    "달걀" to "🥚",
    "대파" to "🌿",
    "밥" to "🍚",
    "김치" to "🥬",
    "두부" to "◻️",
    "양파" to "🧅"
)

@Composable
fun IngredientScreen(
    initial: Set<String> = emptySet(),
    onRecommend: (Set<String>) -> Unit
) {
    var selected by rememberSaveable(initial) { mutableStateOf(ArrayList(initial)) }
    var query by rememberSaveable { mutableStateOf("") }
    val visibleIngredients = ALL_INGREDIENTS.filter { it.contains(query.trim(), ignoreCase = true) }

    val colors = MaterialTheme.colorScheme

    Column(
        Modifier.fillMaxSize().background(colors.background).imePadding()
    ) {
        YellowHeader(
            title = "냉장고에 뭐가 있나요?",
            trailing = {
                Text("냉털", color = colors.onPrimaryContainer, style = MaterialTheme.typography.labelLarge)
            }
        ) {
            TextField(
                value = query,
                onValueChange = { query = it },
                modifier = Modifier.fillMaxWidth(),
                label = { Text("재료 검색") },
                singleLine = true,
                shape = RoundedCornerShape(16.dp),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = colors.surfaceContainerLowest,
                    unfocusedContainerColor = colors.surfaceContainerLowest,
                    focusedTextColor = colors.onSurface,
                    unfocusedTextColor = colors.onSurface,
                    focusedLabelColor = colors.onSurface,
                    unfocusedLabelColor = colors.onSurface,
                    cursorColor = colors.onSurface,
                    focusedIndicatorColor = colors.surfaceContainerLowest,
                    unfocusedIndicatorColor = colors.surfaceContainerLowest
                )
            )
        }

        if (visibleIngredients.isEmpty()) {
            Box(Modifier.weight(1f).fillMaxWidth(), contentAlignment = Alignment.Center) {
                Text("검색 결과가 없어요", color = colors.onBackground)
            }
        } else {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier.weight(1f).fillMaxWidth(),
                contentPadding = PaddingValues(horizontal = 20.dp, vertical = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(visibleIngredients, key = { it }) { name ->
                    IngredientCard(name, name in selected) { checked ->
                        selected = ArrayList(if (checked) selected + name else selected - name)
                    }
                }
            }
        }

        Surface(color = colors.surfaceContainerLowest, contentColor = colors.onSurface, shadowElevation = 4.dp) {
            Column(Modifier.fillMaxWidth().padding(horizontal = 20.dp, vertical = 10.dp)) {
                Row(
                    Modifier.fillMaxWidth().heightIn(min = 48.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("재료 ${selected.size}개 선택", style = MaterialTheme.typography.titleSmall)
                    if (selected.isNotEmpty()) {
                        TextButton(onClick = { selected = arrayListOf() }) {
                            Text("선택 초기화", color = colors.secondary)
                        }
                    }
                }
                PillButton(
                    text = "레시피 추천받기",
                    onClick = { onRecommend(selected.toSet()) },
                    enabled = selected.isNotEmpty(),
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}

@Composable
private fun IngredientCard(name: String, selected: Boolean, onCheckedChange: (Boolean) -> Unit) {
    val colors = MaterialTheme.colorScheme

    Surface(
        shape = RoundedCornerShape(18.dp),
        color = if (selected) colors.secondaryContainer else colors.surfaceContainerLowest,
        contentColor = if (selected) colors.onSecondaryContainer else colors.onSurface,
        border = BorderStroke(1.dp, if (selected) colors.secondary else colors.outlineVariant)
    ) {
        Box(
            Modifier
                .fillMaxWidth()
                .heightIn(min = 108.dp)
                .toggleable(value = selected, role = Role.Checkbox, onValueChange = onCheckedChange)
                .padding(12.dp)
        ) {
            Text(
                if (selected) "✓" else "○",
                modifier = Modifier.align(Alignment.TopEnd).clearAndSetSemantics {}
            )
            Column(
                modifier = Modifier.align(Alignment.Center).padding(horizontal = 14.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                Text(
                    ingredientSymbols[name] ?: "🥣",
                    modifier = Modifier.clearAndSetSemantics {},
                    style = MaterialTheme.typography.headlineSmall
                )
                Text(name, style = MaterialTheme.typography.titleSmall)
            }
        }
    }
}
