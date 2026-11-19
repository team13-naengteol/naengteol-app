package com.team13.naengteol.ui

// 담당: 뭉흐오르길우수흐자르갈 (3번) — 추천 메뉴 화면
// 지금은 작동 확인용 최소 버전. 디자인은 자유롭게 바꾸되 함수 모양은 유지하기

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.team13.naengteol.logic.recommend

@Composable
fun RecommendationScreen(
    selected: Set<String>,
    onRecipeClick: (Int) -> Unit,
    onBack: () -> Unit
) {
    val results = remember(selected) { recommend(selected) }

    Column(Modifier.fillMaxSize().padding(24.dp).verticalScroll(rememberScrollState())) {
        TextButton(onClick = onBack) { Text("← 뒤로") }
        Text("추천 메뉴", style = MaterialTheme.typography.headlineSmall)
        Text("선택한 재료: ${selected.joinToString()}")
        Spacer(Modifier.height(16.dp))

        if (results.isEmpty()) {
            Text("선택한 재료로 만들 수 있는 요리가 없어요. 재료를 더 골라 보세요.")
        }

        results.forEach { r ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 6.dp)
                    .clickable { onRecipeClick(r.recipe.id) }
            ) {
                Column(Modifier.padding(16.dp)) {
                    Text(r.recipe.name, style = MaterialTheme.typography.titleMedium)
                    Text("보유 재료: ${r.owned.joinToString()}")
                    if (r.missing.isNotEmpty()) {
                        Text(
                            "※ ${r.missing.joinToString()}이(가) 필요해요!",
                            color = MaterialTheme.colorScheme.error
                        )
                    }
                }
            }
        }
    }
}
