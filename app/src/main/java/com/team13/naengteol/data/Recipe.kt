package com.team13.naengteol.data

// 담당: 윤정민 (1번) — 공통 데이터 형식. 바꿀 때는 팀 전체와 먼저 상의하기

data class IngredientAmount(
    val name: String,   // 재료 이름 (ALL_INGREDIENTS에 있는 이름과 글자까지 똑같이)
    val amount: String  // 분량 (예: "1개", "1/2모", "1큰술")
)

data class Recipe(
    val id: Int,                             // 요리마다 겹치지 않는 번호
    val name: String,                        // 요리 이름
    val ingredients: List<IngredientAmount>, // 필요한 재료와 분량
    val steps: List<String>                  // 조리 순서 (번호는 화면에서 붙임)
)
