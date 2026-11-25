package com.team13.naengteol.ui.theme

// 담당: 윤정민 (1번) — 모서리 둥글기 (레퍼런스처럼 둥근 카드)
// Card는 따로 지정하지 않아도 medium(20dp)이 자동으로 적용됨

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Shapes
import androidx.compose.ui.unit.dp

val NaengteolShapes = Shapes(
    extraSmall = RoundedCornerShape(8.dp),
    small = RoundedCornerShape(12.dp),   // 칩, 작은 태그
    medium = RoundedCornerShape(20.dp),  // 카드
    large = RoundedCornerShape(28.dp),   // 상단 노란 영역 아래 모서리
    extraLarge = RoundedCornerShape(32.dp)
)
