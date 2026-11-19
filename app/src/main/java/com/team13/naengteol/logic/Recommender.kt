package com.team13.naengteol.logic

// 담당: 뭉흐오르길우수흐자르갈 (3번) — 추천 규칙 (초안, 11/26 미팅에서 확정 후 수정)

import com.team13.naengteol.data.RECIPES
import com.team13.naengteol.data.Recipe

data class RecommendResult(
    val recipe: Recipe,
    val owned: List<String>,   // 가지고 있는 재료
    val missing: List<String>  // 부족한 재료
)

// 기본 양념: 부족한 재료로 표시하지 않음
val BASIC_SEASONINGS: Set<String> = setOf("소금", "식용유", "간장", "설탕", "후추")

fun recommend(selected: Set<String>): List<RecommendResult> {
    return RECIPES
        .map { recipe ->
            val names = recipe.ingredients.map { it.name }.filter { it !in BASIC_SEASONINGS }
            RecommendResult(
                recipe = recipe,
                owned = names.filter { it in selected },
                missing = names.filter { it !in selected }
            )
        }
        .filter { it.owned.isNotEmpty() }              // 선택한 재료가 1개 이상 들어가는 요리만
        .sortedWith(
            compareByDescending<RecommendResult> { it.owned.size } // 겹치는 재료 많은 순
                .thenBy { it.missing.size }                         // 부족한 재료 적은 순
        )
}
