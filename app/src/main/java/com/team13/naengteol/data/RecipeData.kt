package com.team13.naengteol.data

// 담당: 빌구대이 (4번) — 요리 데이터와 선택 가능한 재료 목록

// 재료 선택 화면에 나오는 재료 (목표 30~40개)
val ALL_INGREDIENTS: List<String> = listOf(
    "달걀", "대파", "밥", "김치", "두부", "양파"
    // TODO: 닭고기, 돼지고기, 소고기, 삼겹살, 감자, 당근, 애호박, 고추장, 고춧가루 ... 추가
)

val RECIPES: List<Recipe> = listOf(
    Recipe(
        id = 1,
        name = "달걀볶음밥",
        ingredients = listOf(
            IngredientAmount("달걀", "1개"),
            IngredientAmount("대파", "1/3대"),
            IngredientAmount("밥", "1공기"),
            IngredientAmount("식용유", "1큰술"),
            IngredientAmount("소금", "약간")
        ),
        steps = listOf(
            "달걀을 풀고, 대파를 송송 썰어 준비해요.",
            "팬에 기름을 두르고 대파를 볶다가 달걀을 넣어 익혀요.",
            "밥을 넣고 잘 볶은 뒤 소금으로 간해요."
        )
    )
    // TODO: 닭갈비, 김치찌개, 계란찜, 불고기, 제육볶음, 비빔밥, 삼겹살구이, 두부김치, 김치찜
    //       id는 2, 3, 4 ... 순서대로
)

fun findRecipe(id: Int): Recipe? = RECIPES.find { it.id == id }
