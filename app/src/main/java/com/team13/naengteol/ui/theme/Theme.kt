package com.team13.naengteol.ui.theme

// 담당: 윤정민 (1번) — 앱 전체 테마
// 화면 코드에서는 색을 직접 쓰지 말고 MaterialTheme.colorScheme.primary 처럼 가져다 쓰기
//  - 노란 버튼/영역: primary, primaryContainer (그 위 글자는 onPrimary, onPrimaryContainer)
//  - 흰 배경 위 강조 글자: secondary
//  - "부족" 표시: tertiary

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColorScheme = lightColorScheme(
    primary = Yellow500,
    onPrimary = Ink,
    primaryContainer = Yellow300,
    onPrimaryContainer = Ink,
    secondary = Gold700,
    onSecondary = Color.White,
    secondaryContainer = Yellow100,
    onSecondaryContainer = Ink,
    tertiary = Red600,
    onTertiary = Color.White,
    tertiaryContainer = Red100,
    onTertiaryContainer = Color(0xFF5C120A),
    background = Paper,
    onBackground = Ink,
    surface = Paper,
    onSurface = Ink,
    surfaceVariant = Color(0xFFEFEBE0),
    onSurfaceVariant = Color(0xFF5B574C),
    outline = Color(0xFF8A857A),
    outlineVariant = Color(0xFFE9E1D2),   // 카드 테두리, 구분선 (연한 베이지)
    surfaceContainerLowest = CardWhite,
    surfaceContainerLow = CardWhite,
    surfaceContainer = CardWhite,          // 카드 기본 배경 (레퍼런스처럼 흰 카드)
    surfaceContainerHigh = Color(0xFFF8F5EC),
    surfaceContainerHighest = Color(0xFFF1EDE2)
)

private val DarkColorScheme = darkColorScheme(
    primary = YellowDark,
    onPrimary = YellowDarkOn,
    primaryContainer = Color(0xFF5A4700),
    onPrimaryContainer = Yellow300,
    secondary = Color(0xFFE6C66A),
    tertiary = RedDark,
    outlineVariant = Color(0xFF4A463C),
    background = DarkBackground,
    surface = DarkBackground,
    surfaceContainer = Color(0xFF221F18),
    surfaceContainerHigh = Color(0xFF2C2921),
    surfaceContainerHighest = Color(0xFF37332B)
)

@Composable
fun NaengteolTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    // 휴대폰 배경화면 색을 따라가는 Dynamic Color는 끔
    // → 누구 폰에서 실행해도 냉털 노란색으로 똑같이 보이게
    MaterialTheme(
        colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme,
        typography = Typography,
        shapes = NaengteolShapes,
        content = content
    )
}
