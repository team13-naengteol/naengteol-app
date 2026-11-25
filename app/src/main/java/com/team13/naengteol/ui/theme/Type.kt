package com.team13.naengteol.ui.theme

// 담당: 윤정민 (1번) — 글자 스타일 (글꼴: Pretendard, 무료 글꼴 / 라이선스: licenses/Pretendard-OFL.txt)

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.team13.naengteol.R

val Pretendard = FontFamily(
    Font(R.font.pretendard_regular, FontWeight.Normal),
    Font(R.font.pretendard_semibold, FontWeight.SemiBold),
    Font(R.font.pretendard_bold, FontWeight.Bold)
)

val Typography = Typography(
    // 화면 제목 (예: "냉장고에 뭐가 있나요?", "추천 메뉴", 요리 이름)
    headlineSmall = TextStyle(fontFamily = Pretendard, fontWeight = FontWeight.Bold, fontSize = 24.sp, lineHeight = 32.sp),
    // 섹션 제목 (예: "필요한 재료", "조리 순서")
    titleLarge = TextStyle(fontFamily = Pretendard, fontWeight = FontWeight.Bold, fontSize = 19.sp, lineHeight = 26.sp),
    // 카드 제목 (예: 요리 카드 이름)
    titleMedium = TextStyle(fontFamily = Pretendard, fontWeight = FontWeight.SemiBold, fontSize = 16.sp, lineHeight = 22.sp),
    // 본문
    bodyLarge = TextStyle(fontFamily = Pretendard, fontWeight = FontWeight.Normal, fontSize = 16.sp, lineHeight = 24.sp),
    bodyMedium = TextStyle(fontFamily = Pretendard, fontWeight = FontWeight.Normal, fontSize = 14.sp, lineHeight = 20.sp),
    // 작은 설명 (예: "1/2모", "부족" 표시)
    bodySmall = TextStyle(fontFamily = Pretendard, fontWeight = FontWeight.Normal, fontSize = 12.sp, lineHeight = 16.sp),
    // 버튼, 칩 글자
    labelLarge = TextStyle(fontFamily = Pretendard, fontWeight = FontWeight.SemiBold, fontSize = 15.sp, lineHeight = 20.sp),
    labelMedium = TextStyle(fontFamily = Pretendard, fontWeight = FontWeight.SemiBold, fontSize = 13.sp, lineHeight = 18.sp)
)
