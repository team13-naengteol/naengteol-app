package com.team13.naengteol.ui

// 담당: 뭉흐오르길우수흐자르갈 (3번) — 추천 메뉴 화면
// 지금은 작동 확인용 최소 버전. 디자인은 자유롭게 바꾸되 함수 모양은 유지하기

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.team13.naengteol.logic.recommend
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import com.team13.naengteol.data.RecipeDto
import com.team13.naengteol.data.RecipeRepository
import com.team13.naengteol.logic.recommendApi

// BRAND COLORS
private val BabyYellow = Color(0xFFFFF0B5)
private val Cream = Color(0xFFFFFCF5)
private val Ink = Color(0xFF302E29)
private val Muted = Color(0xFF969087)
private val SoftYellow = Color(0xFFFFF7DC)
private val Coral = Color(0xFFCF7961)
private val BorderSoft = Color(0xFFF0EDE5)

@Composable
fun RecommendationScreen(
    selected: Set<String>,
    onRecipeClick: (Int) -> Unit,
    onBack: () -> Unit
) {
    var recipes by remember {
        mutableStateOf<List<RecipeDto>>(emptyList())
    }

    var isLoading by remember {
        mutableStateOf(true)
    }

    var errorMessage by remember {
        mutableStateOf<String?>(null)
    }

    LaunchedEffect(Unit) {
        try {
            isLoading = true
            errorMessage = null
            recipes = RecipeRepository.getRecipes()
        } catch (e: Exception) {
            errorMessage = e.message ?: "레시피를 불러오지 못했어요."
        } finally {
            isLoading = false
        }
    }

    val results = remember(selected, recipes) {
        recommendApi(selected, recipes)
    }
    Scaffold(
        containerColor = Cream,
        modifier = Modifier.fillMaxSize()
    ) { padding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = padding.calculateBottomPadding())
                .verticalScroll(rememberScrollState())
        ) {

            // HEADER
            Surface(
                modifier = Modifier.fillMaxWidth(),
                color = BabyYellow,
                shape = RoundedCornerShape(
                    bottomStart = 34.dp,
                    bottomEnd = 34.dp
                )
            ) {
                Column(
                    modifier = Modifier
                        .statusBarsPadding()
                        .padding(horizontal = 24.dp)
                        .padding(top = 12.dp, bottom = 26.dp)
                ) {

                    // TOP NAVIGATION
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        TextButton(onClick = onBack) {
                            Text(
                                text = "← 뒤로",
                                color = Ink,
                                fontWeight = FontWeight.Medium
                            )
                        }

                        Spacer(Modifier.weight(1f))

                        Text(
                            text = "NAENGTEOL",
                            fontWeight = FontWeight.ExtraBold,
                            color = Ink,
                            style = MaterialTheme.typography.labelLarge,
                            letterSpacing = 2.sp
                        )
                    }

                    Spacer(Modifier.height(24.dp))

                    Text(
                        text = "YOUR FRIDGE,",
                        color = Ink.copy(alpha = 0.55f),
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 2.sp
                    )

                    Spacer(Modifier.height(8.dp))

                    Text(
                        text = "오늘 뭐 먹지?",
                        color = Ink,
                        style = MaterialTheme.typography.headlineLarge,
                        fontWeight = FontWeight.ExtraBold
                    )

                    Spacer(Modifier.height(8.dp))

                    Text(
                        text = "냉장고 속 재료로 맛있는 한 끼를 만들어 봐요.",
                        color = Ink.copy(alpha = 0.7f),
                        style = MaterialTheme.typography.bodyMedium
                    )

                    Spacer(Modifier.height(24.dp))

                    // INGREDIENT SECTION
                    Surface(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(22.dp),
                        color = Color.White
                    ) {
                        Column(
                            modifier = Modifier.padding(18.dp)
                        ) {

                            Row(
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = "MY INGREDIENTS",
                                    style = MaterialTheme.typography.labelMedium,
                                    color = Muted,
                                    fontWeight = FontWeight.Bold,
                                    letterSpacing = 1.sp
                                )

                                Spacer(Modifier.weight(1f))

                                Text(
                                    text = "${selected.size} ITEMS",
                                    color = Ink,
                                    style = MaterialTheme.typography.labelSmall,
                                    fontWeight = FontWeight.Bold
                                )
                            }

                            Spacer(Modifier.height(14.dp))

                            if (selected.isEmpty()) {
                                Text(
                                    text = "아직 선택한 재료가 없어요.",
                                    color = Muted,
                                    style = MaterialTheme.typography.bodyMedium
                                )
                            } else {
                                Row(
                                    modifier = Modifier.horizontalScroll(
                                        rememberScrollState()
                                    ),
                                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                                ) {
                                    selected.sorted().forEach { ingredient ->
                                        Surface(
                                            shape = CircleShape,
                                            color = SoftYellow
                                        ) {
                                            Text(
                                                text = ingredient,
                                                modifier = Modifier.padding(
                                                    horizontal = 14.dp,
                                                    vertical = 9.dp
                                                ),
                                                color = Ink,
                                                style = MaterialTheme.typography.labelLarge,
                                                fontWeight = FontWeight.Medium
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }

            // MAIN CONTENT
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp)
                    .padding(top = 30.dp, bottom = 32.dp)
            ) {

                // SECTION TITLE
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = "추천 메뉴",
                            style = MaterialTheme.typography.headlineSmall,
                            fontWeight = FontWeight.ExtraBold,
                            color = Ink
                        )

                        Spacer(Modifier.height(5.dp))

                        Text(
                            text = "당신의 재료로 만든 오늘의 추천",
                            style = MaterialTheme.typography.bodySmall,
                            color = Muted
                        )
                    }

                    Spacer(Modifier.weight(1f))

                    Surface(
                        color = SoftYellow,
                        shape = CircleShape
                    ) {
                        Text(
                            text = "${results.size}개",
                            modifier = Modifier.padding(
                                horizontal = 13.dp,
                                vertical = 8.dp
                            ),
                            color = Ink,
                            style = MaterialTheme.typography.labelMedium,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }

                Spacer(Modifier.height(22.dp))

                // EMPTY STATE
                if (isLoading) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(40.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                } else if (errorMessage != null) {
                    Text(
                        text = "레시피를 불러오지 못했어요: $errorMessage",
                        color = MaterialTheme.colorScheme.error
                    )
                } else if (results.isEmpty()) {

                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(26.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = Color.White
                        ),
                        elevation = CardDefaults.cardElevation(
                            defaultElevation = 0.dp
                        )
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 24.dp)
                                .padding(vertical = 32.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {

                            // CUSTOM PLATE ILLUSTRATION
                            Box(
                                modifier = Modifier
                                    .size(112.dp)
                                    .background(
                                        SoftYellow,
                                        CircleShape
                                    ),
                                contentAlignment = Alignment.Center
                            ) {
                                Surface(
                                    modifier = Modifier.size(76.dp),
                                    color = Color.White,
                                    shape = CircleShape,
                                    shadowElevation = 2.dp
                                ) {
                                    Box(
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Text(
                                            text = "🍳",
                                            style = MaterialTheme.typography.headlineLarge
                                        )
                                    }
                                }
                            }

                            Spacer(Modifier.height(24.dp))

                            Text(
                                text = "아직 추천 메뉴가 없어요",
                                color = Ink,
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold
                            )

                            Spacer(Modifier.height(9.dp))

                            Text(
                                text = "재료를 조금 더 선택하면\n맛있는 메뉴를 찾아드릴게요.",
                                color = Muted,
                                style = MaterialTheme.typography.bodyMedium,
                                lineHeight = MaterialTheme.typography.bodyMedium.lineHeight,
                                textAlign = androidx.compose.ui.text.style.TextAlign.Center
                            )

                            Spacer(Modifier.height(20.dp))

                            Surface(
                                shape = CircleShape,
                                color = BabyYellow
                            ) {
                                Text(
                                    text = "맛있는 한 끼를 준비해 봐요",
                                    modifier = Modifier.padding(
                                        horizontal = 18.dp,
                                        vertical = 11.dp
                                    ),
                                    color = Ink,
                                    style = MaterialTheme.typography.labelMedium,
                                    fontWeight = FontWeight.SemiBold
                                )
                            }
                        }
                    }

                } else {

                    // RECIPE CARDS
                    results.forEach { r ->

                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 16.dp)
                                .clickable {
                                    onRecipeClick(r.recipe.id)
                                },
                            shape = RoundedCornerShape(24.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = Color.White
                            ),
                            elevation = CardDefaults.cardElevation(
                                defaultElevation = 1.dp
                            )
                        ) {

                            Column(
                                modifier = Modifier.padding(20.dp)
                            ) {

                                Row(
                                    verticalAlignment = Alignment.CenterVertically
                                ) {

                                    // TEMPORARY FOOD IMAGE
                                    Box(
                                        modifier = Modifier
                                            .size(76.dp)
                                            .background(
                                                SoftYellow,
                                                RoundedCornerShape(18.dp)
                                            ),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Text(
                                            text = "🍳",
                                            style = MaterialTheme.typography.displaySmall
                                        )
                                    }

                                    Spacer(Modifier.width(16.dp))

                                    Column(
                                        modifier = Modifier.weight(1f)
                                    ) {
                                        Text(
                                            text = r.recipe.name,
                                            style = MaterialTheme.typography.titleMedium,
                                            fontWeight = FontWeight.Bold,
                                            color = Ink
                                        )

                                        Spacer(Modifier.height(7.dp))

                                        Text(
                                            text = "${r.owned.size}개 재료 보유",
                                            style = MaterialTheme.typography.bodySmall,
                                            color = Muted
                                        )

                                        Spacer(Modifier.height(8.dp))

                                        Text(
                                            text = "자세히 보기  →",
                                            color = Ink,
                                            style = MaterialTheme.typography.labelMedium,
                                            fontWeight = FontWeight.Bold
                                        )
                                    }
                                }

                                Spacer(Modifier.height(16.dp))

                                HorizontalDivider(color = BorderSoft)

                                Spacer(Modifier.height(14.dp))

                                Text(
                                    text = "보유 재료",
                                    color = Muted,
                                    style = MaterialTheme.typography.labelMedium,
                                    fontWeight = FontWeight.Bold
                                )

                                Spacer(Modifier.height(6.dp))

                                Text(
                                    text = r.owned.joinToString(" · "),
                                    color = Ink,
                                    style = MaterialTheme.typography.bodyMedium
                                )

                                if (r.missing.isNotEmpty()) {
                                    Spacer(Modifier.height(12.dp))

                                    Text(
                                        text = "추가 필요  ·  ${r.missing.joinToString(", ")}",
                                        color = Coral,
                                        style = MaterialTheme.typography.bodySmall,
                                        fontWeight = FontWeight.Medium
                                    )
                                } else {
                                    Spacer(Modifier.height(12.dp))

                                    Text(
                                        text = "✓ 모든 재료를 가지고 있어요!",
                                        color = Color(0xFF66866A),
                                        style = MaterialTheme.typography.bodySmall,
                                        fontWeight = FontWeight.Bold
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun RecommendationScreenPreview() {
    RecommendationScreen(
        selected = setOf("계란", "토마토", "양파"),
        onRecipeClick = {},
        onBack = {}
    )
}

