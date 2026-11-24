package com.team13.naengteol.ui.components

// 담당: 윤정민 (1번) — 모든 화면에서 같이 쓰는 공통 디자인 부품 (레퍼런스 스타일)
// 각자 화면에서 가져다 쓰기만 하고, 이 파일을 고쳐야 하면 1번에게 요청하기
//
// 사용 예:
//   YellowHeader(title = "추천 메뉴", onBack = onBack)
//   NaengteolCard(onClick = { ... }) { Text("달걀볶음밥") }
//   PillButton(text = "레시피 추천받기", onClick = { ... }, enabled = selected.isNotEmpty())
//   MissingLabel("밥 부족")

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

/** 화면 위쪽의 노란 영역 (제목 + 뒤로가기 + 오른쪽 버튼 + 아래 추가 내용) */
@Composable
fun YellowHeader(
    title: String,
    modifier: Modifier = Modifier,
    subtitle: String? = null,
    onBack: (() -> Unit)? = null,
    trailing: (@Composable () -> Unit)? = null,
    bottomContent: (@Composable () -> Unit)? = null // 예: 선택한 재료 칩, 검색창
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(bottomStart = 28.dp, bottomEnd = 28.dp))
            .background(MaterialTheme.colorScheme.primaryContainer)
            .padding(start = 20.dp, end = 20.dp, top = 16.dp, bottom = 20.dp)
    ) {
        if (onBack != null) {
            BackCircleButton(onClick = onBack)
            Spacer(Modifier.height(12.dp))
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            Column(Modifier.weight(1f)) {
                Text(
                    title,
                    style = MaterialTheme.typography.headlineSmall,
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )
                if (subtitle != null) {
                    Text(
                        subtitle,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                }
            }
            if (trailing != null) trailing()
        }
        if (bottomContent != null) {
            Spacer(Modifier.height(16.dp))
            bottomContent()
        }
    }
}

/** 흰 동그라미 뒤로가기 버튼 */
@Composable
fun BackCircleButton(onClick: () -> Unit, modifier: Modifier = Modifier) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .size(40.dp)
            .shadow(2.dp, CircleShape)
            .clip(CircleShape)
            .background(Color.White)
            .clickable(onClick = onClick)
    ) {
        Text("←", style = MaterialTheme.typography.titleMedium, color = MaterialTheme.colorScheme.onSurface)
    }
}

/** 그림자 있는 흰 카드 (요리 카드, 재료 카드 등) */
@Composable
fun NaengteolCard(
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null,
    content: @Composable ColumnScope.() -> Unit
) {
    val shape = MaterialTheme.shapes.medium
    Column(
        modifier = modifier
            .shadow(4.dp, shape)
            .clip(shape)
            .background(MaterialTheme.colorScheme.surfaceContainerLowest)
            .then(if (onClick != null) Modifier.clickable(onClick = onClick) else Modifier)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp),
        content = content
    )
}

/** 노란 알약 모양 버튼 */
@Composable
fun PillButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true
) {
    Button(
        onClick = onClick,
        enabled = enabled,
        shape = CircleShape,
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary
        ),
        modifier = modifier.height(52.dp)
    ) {
        Text(text, style = MaterialTheme.typography.labelLarge)
    }
}

/** 작은 알약 모양 선택 칩 (예: 재료, 필터) */
@Composable
fun SelectChip(
    text: String,
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val bg = if (selected) MaterialTheme.colorScheme.onSurface else Color.White
    val fg = if (selected) Color.White else MaterialTheme.colorScheme.onSurface
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .clip(CircleShape)
            .background(bg)
            .clickable(onClick = onClick)
            .padding(horizontal = 14.dp, vertical = 8.dp)
    ) {
        Text(text, style = MaterialTheme.typography.labelMedium, color = fg)
    }
}

/** "부족" 같은 빨간 상태 글자 (레퍼런스의 EXPIRED 표시처럼) */
@Composable
fun MissingLabel(text: String, modifier: Modifier = Modifier) {
    Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {
        Box(
            Modifier
                .size(6.dp)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.tertiary)
        )
        Spacer(Modifier.width(6.dp))
        Text(
            text,
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.tertiary
        )
    }
}
