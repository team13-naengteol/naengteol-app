package com.team13.naengteol.ui.theme

// 담당: 윤정민 (1번) — 앱 전체 테마
// 화면 코드에서는 색을 직접 쓰지 말고 MaterialTheme.colorScheme.primary 처럼 가져다 쓰기

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColorScheme = lightColorScheme(
    primary = Green700,
    onPrimary = Color.White,
    primaryContainer = Green100,
    onPrimaryContainer = Green900,
    secondary = Green500,
    onSecondary = Color.White,
    secondaryContainer = Green50,
    onSecondaryContainer = Green900,
    tertiary = Orange600,          // "부족" 표시
    onTertiary = Color.White,
    tertiaryContainer = Orange100,
    onTertiaryContainer = Color(0xFF5A2408),
    background = Cream,
    onBackground = Ink,
    surface = Cream,
    onSurface = Ink,
    surfaceVariant = Color(0xFFE1E8DA),
    onSurfaceVariant = Color(0xFF444A40),
    outline = Color(0xFF747A6F),
    surfaceContainerLowest = Color.White,
    surfaceContainerLow = Color(0xFFF4F8EF),
    surfaceContainer = Color(0xFFEEF3E8), // 카드 기본 배경
    surfaceContainerHigh = Color(0xFFE8EEE2),
    surfaceContainerHighest = Color(0xFFE2E9DC)
)

private val DarkColorScheme = darkColorScheme(
    primary = GreenDarkPrimary,
    onPrimary = GreenDarkOnPrimary,
    primaryContainer = Green700,
    onPrimaryContainer = Green100,
    secondary = Color(0xFFB6CCB0),
    tertiary = OrangeDark,
    background = DarkBackground,
    surface = DarkBackground,
    surfaceContainer = Color(0xFF1E211B),
    surfaceContainerHigh = DarkSurfaceHigh,
    surfaceContainerHighest = Color(0xFF33382F)
)

@Composable
fun NaengteolTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    // 휴대폰 배경화면 색을 따라가는 Dynamic Color는 끔
    // → 누구 폰에서 실행해도 냉털 초록색으로 똑같이 보이게
    MaterialTheme(
        colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme,
        typography = Typography,
        content = content
    )
}
