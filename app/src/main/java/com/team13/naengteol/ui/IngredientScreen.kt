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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.unit.dp
import com.team13.naengteol.data.ALL_INGREDIENTS

private val HeaderYellow = Color(0xFFFFDF79)
private val SelectedYellow = Color(0xFFFFF2C3)
private val Ink = Color(0xFF322916)
private val PageBackground = Color(0xFFFFFCF6)

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

    Column(
        Modifier.fillMaxSize().background(PageBackground).imePadding()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(HeaderYellow, RoundedCornerShape(bottomStart = 24.dp, bottomEnd = 24.dp))
                .padding(horizontal = 20.dp, vertical = 18.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Text("냉털", color = Ink, style = MaterialTheme.typography.labelLarge)
            Text("냉장고에 뭐가 있나요?", color = Ink, style = MaterialTheme.typography.headlineSmall)
            TextField(
                value = query,
                onValueChange = { query = it },
                modifier = Modifier.fillMaxWidth(),
                label = { Text("재료 검색") },
                singleLine = true,
                shape = RoundedCornerShape(16.dp),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    focusedTextColor = Ink,
                    unfocusedTextColor = Ink,
                    focusedLabelColor = Ink,
                    unfocusedLabelColor = Ink,
                    cursorColor = Ink,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                )
            )
        }

        if (visibleIngredients.isEmpty()) {
            Box(Modifier.weight(1f).fillMaxWidth(), contentAlignment = Alignment.Center) {
                Text("검색 결과가 없어요", color = Ink)
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

        Surface(color = Color.White, contentColor = Ink, shadowElevation = 4.dp) {
            Column(Modifier.fillMaxWidth().padding(horizontal = 20.dp, vertical = 10.dp)) {
                Row(
                    Modifier.fillMaxWidth().heightIn(min = 48.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("재료 ${selected.size}개 선택", style = MaterialTheme.typography.titleSmall)
                    if (selected.isNotEmpty()) {
                        TextButton(onClick = { selected = arrayListOf() }) {
                            Text("선택 초기화", color = Ink)
                        }
                    }
                }
                Button(
                    onClick = { onRecommend(selected.toSet()) },
                    enabled = selected.isNotEmpty(),
                    modifier = Modifier.fillMaxWidth().heightIn(min = 48.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = HeaderYellow,
                        contentColor = Ink,
                        disabledContainerColor = Color(0xFFF1EEE7),
                        disabledContentColor = Color(0xFF6E6659)
                    )
                ) {
                    Text("레시피 추천받기")
                }
            }
        }
    }
}

@Composable
private fun IngredientCard(name: String, selected: Boolean, onCheckedChange: (Boolean) -> Unit) {
    Surface(
        shape = RoundedCornerShape(18.dp),
        color = if (selected) SelectedYellow else Color.White,
        contentColor = Ink,
        border = BorderStroke(1.dp, if (selected) Color(0xFF997020) else Color(0xFFE9E1D2))
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
