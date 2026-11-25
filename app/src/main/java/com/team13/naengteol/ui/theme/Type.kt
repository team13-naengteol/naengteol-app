package com.team13.naengteol.ui.theme

// 담당: 윤정민 (1번) — 글자 스타일 (글꼴: Pretendard, 무료 글꼴 / 라이선스: licenses/Pretendard-OFL.txt)
// Material의 글자 스타일 15개 전부 Pretendard로 맞춤 → 어떤 스타일을 써도 글꼴이 같게 나옴

import androidx.compose.material3.Typography
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

private val Base = Typography()

val Typography = Typography(
    displayLarge = Base.displayLarge.copy(fontFamily = Pretendard, fontWeight = FontWeight.Bold),
    displayMedium = Base.displayMedium.copy(fontFamily = Pretendard, fontWeight = FontWeight.Bold),
    displaySmall = Base.displaySmall.copy(fontFamily = Pretendard, fontWeight = FontWeight.Bold),
    headlineLarge = Base.headlineLarge.copy(fontFamily = Pretendard, fontWeight = FontWeight.Bold),
    headlineMedium = Base.headlineMedium.copy(fontFamily = Pretendard, fontWeight = FontWeight.Bold),
    // 화면 제목 (예: "냉장고에 뭐가 있나요?", "추천 메뉴", 요리 이름)
    headlineSmall = Base.headlineSmall.copy(fontFamily = Pretendard, fontWeight = FontWeight.Bold, fontSize = 24.sp, lineHeight = 32.sp),
    // 섹션 제목 (예: "필요한 재료", "조리 순서")
    titleLarge = Base.titleLarge.copy(fontFamily = Pretendard, fontWeight = FontWeight.Bold, fontSize = 19.sp, lineHeight = 26.sp),
    // 카드 제목 (예: 요리 카드 이름)
    titleMedium = Base.titleMedium.copy(fontFamily = Pretendard, fontWeight = FontWeight.SemiBold, fontSize = 16.sp, lineHeight = 22.sp, letterSpacing = 0.sp),
    // 작은 제목 (예: 재료 카드 이름, "재료 N개 선택")
    titleSmall = Base.titleSmall.copy(fontFamily = Pretendard, fontWeight = FontWeight.SemiBold, fontSize = 14.sp, lineHeight = 20.sp, letterSpacing = 0.sp),
    // 본문
    bodyLarge = Base.bodyLarge.copy(fontFamily = Pretendard, fontWeight = FontWeight.Normal, fontSize = 16.sp, lineHeight = 24.sp),
    bodyMedium = Base.bodyMedium.copy(fontFamily = Pretendard, fontWeight = FontWeight.Normal, fontSize = 14.sp, lineHeight = 20.sp),
    // 작은 설명 (예: "1/2모")
    bodySmall = Base.bodySmall.copy(fontFamily = Pretendard, fontWeight = FontWeight.Normal, fontSize = 12.sp, lineHeight = 16.sp),
    // 버튼, 칩, 라벨 글자
    labelLarge = Base.labelLarge.copy(fontFamily = Pretendard, fontWeight = FontWeight.SemiBold, fontSize = 15.sp, lineHeight = 20.sp),
    labelMedium = Base.labelMedium.copy(fontFamily = Pretendard, fontWeight = FontWeight.SemiBold, fontSize = 13.sp, lineHeight = 18.sp),
    labelSmall = Base.labelSmall.copy(fontFamily = Pretendard, fontWeight = FontWeight.SemiBold)
)
