package com.team13.naengteol

// 담당: 윤정민 (1번) — 세 화면 연결. 다른 사람은 이 파일을 고치지 말고 1번에게 요청하기

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.team13.naengteol.ui.IngredientScreen
import com.team13.naengteol.ui.RecipeDetailScreen
import com.team13.naengteol.ui.RecommendationScreen
import com.team13.naengteol.ui.theme.NaengteolTheme

// 앱에 있는 화면 3개
sealed interface Screen {
    object Ingredient : Screen
    data class Recommendation(val selected: Set<String>) : Screen
    data class Detail(val recipeId: Int, val selected: Set<String>) : Screen
}

// 화면 순서 (앞으로 가는지 뒤로 가는지 판단해서 애니메이션 방향을 정할 때 사용)
private val Screen.depth: Int
    get() = when (this) {
        Screen.Ingredient -> 0
        is Screen.Recommendation -> 1
        is Screen.Detail -> 2
    }

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NaengteolTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NaengteolApp(Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun NaengteolApp(modifier: Modifier = Modifier) {
    var screen by remember { mutableStateOf<Screen>(Screen.Ingredient) }
    var lastSelected by remember { mutableStateOf(setOf<String>()) } // 뒤로 왔을 때 체크 유지용

    // 휴대폰의 뒤로가기 버튼
    BackHandler(enabled = screen != Screen.Ingredient) {
        screen = when (val s = screen) {
            is Screen.Detail -> Screen.Recommendation(s.selected)
            else -> Screen.Ingredient
        }
    }

    // 화면 전환 애니메이션: 앞으로 갈 때는 오른쪽에서, 뒤로 갈 때는 왼쪽에서 들어옴
    AnimatedContent(
        targetState = screen,
        modifier = modifier,
        transitionSpec = {
            if (targetState.depth > initialState.depth) {
                (slideInHorizontally { it } + fadeIn()) togetherWith
                    (slideOutHorizontally { -it / 3 } + fadeOut())
            } else {
                (slideInHorizontally { -it / 3 } + fadeIn()) togetherWith
                    (slideOutHorizontally { it } + fadeOut())
            }
        },
        label = "screen"
    ) { s ->
        when (s) {
            Screen.Ingredient -> IngredientScreen(
                initial = lastSelected,
                onRecommend = { selected ->
                    lastSelected = selected
                    screen = Screen.Recommendation(selected)
                }
            )
            is Screen.Recommendation -> RecommendationScreen(
                selected = s.selected,
                onRecipeClick = { id -> screen = Screen.Detail(id, s.selected) },
                onBack = { screen = Screen.Ingredient }
            )
            is Screen.Detail -> RecipeDetailScreen(
                recipeId = s.recipeId,
                selected = s.selected,
                onBack = { screen = Screen.Recommendation(s.selected) }
            )
        }
    }
}
