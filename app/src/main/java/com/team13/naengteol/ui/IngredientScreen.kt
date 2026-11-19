package com.team13.naengteol.ui

// 담당: 심여진 (2번) — 재료 선택 화면
// 지금은 작동 확인용 최소 버전. 디자인(재료 카드 그리드 등)은 자유롭게 바꾸되 함수 모양은 유지하기

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.team13.naengteol.data.ALL_INGREDIENTS

@Composable
fun IngredientScreen(
    initial: Set<String> = emptySet(),
    onRecommend: (Set<String>) -> Unit
) {
    var selected by remember { mutableStateOf(initial) }

    Column(Modifier.fillMaxSize().padding(24.dp)) {
        Text("냉장고에 뭐가 있나요?", style = MaterialTheme.typography.headlineSmall)
        Text("가지고 있는 재료를 골라주세요")
        Spacer(Modifier.height(16.dp))

        // TODO: 체크박스 목록 → 재료 카드 그리드로 바꾸기
        Column(Modifier.weight(1f).verticalScroll(rememberScrollState())) {
            ALL_INGREDIENTS.forEach { name ->
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Checkbox(
                        checked = name in selected,
                        onCheckedChange = { checked ->
                            selected = if (checked) selected + name else selected - name
                        }
                    )
                    Text(name)
                }
            }
        }

        Text("선택한 재료 ${selected.size}개")
        Button(
            onClick = { onRecommend(selected) },
            enabled = selected.isNotEmpty(), // 1개 이상 골라야 활성화
            modifier = Modifier.fillMaxWidth()
        ) { Text("레시피 추천받기") }
    }
}
