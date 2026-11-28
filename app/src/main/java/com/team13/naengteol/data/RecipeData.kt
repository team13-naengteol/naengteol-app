package com.team13.naengteol.data

// 담당: 빌구대이 (4번) — 요리 데이터와 선택 가능한 재료 목록

// 재료 선택 화면에 나오는 재료 (목표 30~40개)
val ALL_INGREDIENTS: List<String> = listOf(
    "양파", "대파", "마늘", "감자", "당근",
    "애호박", "양배추", "깻잎", "청양고추", "콩나물",
    "시금치", "버섯", "무", "돼지고기", "삼겹살",
    "소고기", "닭고기", "햄", "소시지", "참치캔",
    "어묵", "달걀", "두부", "김치", "밥",
    "라면", "떡", "당면", "치즈", "고추장",
    "된장", "고춧가루", "참기름"
)
val INGREDIENT_EMOJI: Map<String, String> = mapOf(
    "양파" to "🧅",
    "대파" to "🌿",
    "마늘" to "🧄",
    "감자" to "🥔",
    "당근" to "🥕",
    "애호박" to "🥒",
    "양배추" to "🥬",
    "깻잎" to "🍃",
    "청양고추" to "🌶️",
    "콩나물" to "🌱",
    "시금치" to "🥬",
    "버섯" to "🍄",
    "무" to "🥕",
    "돼지고기" to "🥩",
    "삼겹살" to "🥓",
    "소고기" to "🥩",
    "닭고기" to "🍗",
    "햄" to "🍖",
    "소시지" to "🌭",
    "참치캔" to "🥫",
    "어묵" to "🍢",
    "달걀" to "🥚",
    "두부" to "🧊",
    "김치" to "🥬",
    "밥" to "🍚",
    "라면" to "🍜",
    "떡" to "🍡",
    "당면" to "🍜",
    "치즈" to "🧀",
    "고추장" to "🥣",
    "된장" to "🫘",
    "고춧가루" to "🌶️",
    "참기름" to "🫙"
)

fun emojiOf(name: String): String =
    INGREDIENT_EMOJI[name] ?: "🍽️"
val RECIPES: List<Recipe> = listOf(
    Recipe(
        id = 1,
        name = "달걀볶음밥",
        ingredients = listOf(
            IngredientAmount("달걀", "1개"),
            IngredientAmount("대파", "1/3대"),
            IngredientAmount("밥", "1공기")
        ),
        steps = listOf(
            "달걀과 대파를 준비해요.",
            "대파와 달걀을 볶아 익혀요.",
            "밥을 넣고 함께 볶아 완성해요."
        )
    ),
    Recipe(
        id = 2,
        name = "계란찜",
        ingredients = listOf(
            IngredientAmount("달걀", "3개"),
            IngredientAmount("대파", "약간")
        ),
        steps = listOf(
            "달걀을 풀고 물을 조금 넣어 섞어요.",
            "대파를 잘게 썰어 넣어요.",
            "부드럽게 익혀 계란찜을 완성해요."
        )
    ),
    Recipe(
        id = 3,
        name = "김치찌개",
        ingredients = listOf(
            IngredientAmount("김치", "1컵"),
            IngredientAmount("돼지고기", "100g"),
            IngredientAmount("두부", "1/2모"),
            IngredientAmount("양파", "1/2개"),
            IngredientAmount("대파", "약간"),
            IngredientAmount("고춧가루", "1큰술")
        ),
        steps = listOf(
            "김치와 돼지고기, 양파를 준비해요.",
            "재료를 냄비에 넣고 물을 부어 끓여요.",
            "두부와 대파를 넣고 더 끓여 완성해요."
        )
    ),
    Recipe(
        id = 4,
        name = "불고기",
        ingredients = listOf(
            IngredientAmount("소고기", "200g"),
            IngredientAmount("양파", "1/2개"),
            IngredientAmount("대파", "약간"),
            IngredientAmount("당근", "1/3개"),
            IngredientAmount("마늘", "2쪽"),
            IngredientAmount("참기름", "1큰술")
        ),
        steps = listOf(
            "소고기와 채소를 먹기 좋게 준비해요.",
            "고기에 양념을 넣어 골고루 섞어요.",
            "채소와 함께 익혀 완성해요."
        )
    ),
    Recipe(
        id = 5,
        name = "제육볶음",
        ingredients = listOf(
            IngredientAmount("돼지고기", "200g"),
            IngredientAmount("양파", "1/2개"),
            IngredientAmount("대파", "약간"),
            IngredientAmount("마늘", "2쪽"),
            IngredientAmount("고추장", "1큰술"),
            IngredientAmount("고춧가루", "1큰술")
        ),
        steps = listOf(
            "돼지고기와 채소를 준비해요.",
            "고추장과 고춧가루로 양념해요.",
            "고기와 채소를 함께 익혀 완성해요."
        )
    ),
    Recipe(
        id = 6,
        name = "닭갈비",
        ingredients = listOf(
            IngredientAmount("닭고기", "200g"),
            IngredientAmount("양배추", "1/4개"),
            IngredientAmount("양파", "1/2개"),
            IngredientAmount("깻잎", "약간"),
            IngredientAmount("떡", "한 줌"),
            IngredientAmount("고추장", "1큰술"),
            IngredientAmount("고춧가루", "1큰술")
        ),
        steps = listOf(
            "닭고기와 채소를 준비해요.",
            "닭고기를 양념에 버무려요.",
            "채소와 떡을 넣고 충분히 익혀 완성해요."
        )
    ),
    Recipe(
        id = 7,
        name = "비빔밥",
        ingredients = listOf(
            IngredientAmount("밥", "1공기"),
            IngredientAmount("시금치", "한 줌"),
            IngredientAmount("콩나물", "한 줌"),
            IngredientAmount("당근", "1/3개"),
            IngredientAmount("달걀", "1개"),
            IngredientAmount("고추장", "1큰술"),
            IngredientAmount("참기름", "1큰술")
        ),
        steps = listOf(
            "채소를 준비하고 각각 익혀요.",
            "밥 위에 채소와 달걀을 올려요.",
            "고추장과 참기름을 넣고 비벼 완성해요."
        )
    ),
    Recipe(
        id = 8,
        name = "삼겹살구이",
        ingredients = listOf(
            IngredientAmount("삼겹살", "200g"),
            IngredientAmount("마늘", "3쪽"),
            IngredientAmount("깻잎", "약간"),
            IngredientAmount("청양고추", "1개")
        ),
        steps = listOf(
            "삼겹살과 곁들일 재료를 준비해요.",
            "삼겹살을 충분히 익혀요.",
            "마늘과 채소를 곁들여 완성해요."
        )
    ),
    Recipe(
        id = 9,
        name = "두부김치",
        ingredients = listOf(
            IngredientAmount("두부", "1모"),
            IngredientAmount("김치", "1컵"),
            IngredientAmount("돼지고기", "100g"),
            IngredientAmount("양파", "1/2개"),
            IngredientAmount("대파", "약간")
        ),
        steps = listOf(
            "두부와 김치, 채소를 준비해요.",
            "김치와 돼지고기, 양파를 볶아요.",
            "두부를 데워 볶은 김치와 함께 완성해요."
        )
    ),
    Recipe(
        id = 10,
        name = "김치찜",
        ingredients = listOf(
            IngredientAmount("김치", "1/2포기"),
            IngredientAmount("돼지고기", "200g"),
            IngredientAmount("양파", "1/2개"),
            IngredientAmount("대파", "약간")
        ),
        steps = listOf(
            "김치와 돼지고기, 채소를 준비해요.",
            "재료를 냄비에 넣고 물을 부어요.",
            "재료가 부드러워질 때까지 익혀 완성해요."
        )
    ),
    Recipe(
        id = 11,
        name = "김치볶음밥",
        ingredients = listOf(
            IngredientAmount("밥", "1공기"),
            IngredientAmount("김치", "1/2컵"),
            IngredientAmount("햄", "50g"),
            IngredientAmount("달걀", "1개"),
            IngredientAmount("대파", "약간")
        ),
        steps = listOf(
            "김치와 햄, 대파를 준비해요.",
            "김치와 햄, 대파를 볶아요.",
            "밥을 넣고 볶은 뒤 달걀을 곁들여 완성해요."
        )
    ),
    Recipe(
        id = 12,
        name = "된장찌개",
        ingredients = listOf(
            IngredientAmount("된장", "2큰술"),
            IngredientAmount("두부", "1/2모"),
            IngredientAmount("애호박", "1/2개"),
            IngredientAmount("감자", "1개"),
            IngredientAmount("양파", "1/2개"),
            IngredientAmount("청양고추", "1개")
        ),
        steps = listOf(
            "채소와 두부를 먹기 좋게 썰어요.",
            "냄비에 물과 된장을 넣고 끓여요.",
            "채소와 두부를 넣고 익혀 완성해요."
        )
    ),
    Recipe(
        id = 13,
        name = "계란말이",
        ingredients = listOf(
            IngredientAmount("달걀", "3개"),
            IngredientAmount("대파", "약간"),
            IngredientAmount("당근", "약간")
        ),
        steps = listOf(
            "달걀을 풀고 대파와 당근을 잘게 썰어 섞어요.",
            "달걀물을 나누어 익혀가며 말아요.",
            "먹기 좋은 크기로 썰어 완성해요."
        )
    ),
    Recipe(
        id = 14,
        name = "부대찌개",
        ingredients = listOf(
            IngredientAmount("햄", "100g"),
            IngredientAmount("소시지", "100g"),
            IngredientAmount("김치", "1/2컵"),
            IngredientAmount("라면", "1개"),
            IngredientAmount("두부", "1/2모"),
            IngredientAmount("대파", "약간"),
            IngredientAmount("고춧가루", "1큰술")
        ),
        steps = listOf(
            "햄, 소시지, 김치와 채소를 준비해요.",
            "재료를 냄비에 넣고 국물을 끓여요.",
            "두부와 라면을 넣고 충분히 익혀 완성해요."
        )
    ),
    Recipe(
        id = 15,
        name = "떡볶이",
        ingredients = listOf(
            IngredientAmount("떡", "200g"),
            IngredientAmount("어묵", "100g"),
            IngredientAmount("양배추", "약간"),
            IngredientAmount("대파", "약간"),
            IngredientAmount("고추장", "2큰술"),
            IngredientAmount("고춧가루", "1큰술")
        ),
        steps = listOf(
            "떡과 어묵, 채소를 준비해요.",
            "물에 고추장과 고춧가루를 풀어 끓여요.",
            "떡과 어묵, 채소를 넣고 익혀 완성해요."
        )
    ),
    Recipe(
        id = 16,
        name = "참치김치찌개",
        ingredients = listOf(
            IngredientAmount("참치캔", "1캔"),
            IngredientAmount("김치", "1컵"),
            IngredientAmount("두부", "1/2모"),
            IngredientAmount("양파", "1/2개"),
            IngredientAmount("대파", "약간")
        ),
        steps = listOf(
            "김치와 양파, 두부를 준비해요.",
            "김치와 양파를 냄비에 넣고 끓여요.",
            "참치와 두부, 대파를 넣고 더 끓여 완성해요."
        )
    ),
    Recipe(
        id = 17,
        name = "어묵볶음",
        ingredients = listOf(
            IngredientAmount("어묵", "150g"),
            IngredientAmount("양파", "1/2개"),
            IngredientAmount("당근", "약간")
        ),
        steps = listOf(
            "어묵과 채소를 먹기 좋게 썰어요.",
            "어묵과 채소를 함께 볶아요.",
            "양념을 넣고 골고루 섞어 완성해요."
        )
    ),
    Recipe(
        id = 18,
        name = "감자조림",
        ingredients = listOf(
            IngredientAmount("감자", "2개"),
            IngredientAmount("당근", "약간"),
            IngredientAmount("양파", "1/2개")
        ),
        steps = listOf(
            "감자와 채소를 먹기 좋게 썰어요.",
            "재료와 양념을 냄비에 넣어요.",
            "재료가 부드러워질 때까지 조려 완성해요."
        )
    ),
    Recipe(
        id = 19,
        name = "콩나물국",
        ingredients = listOf(
            IngredientAmount("콩나물", "150g"),
            IngredientAmount("대파", "약간"),
            IngredientAmount("마늘", "2쪽")
        ),
        steps = listOf(
            "콩나물과 대파, 마늘을 준비해요.",
            "냄비에 물과 콩나물을 넣고 끓여요.",
            "마늘과 대파를 넣고 익혀 완성해요."
        )
    ),
    Recipe(
        id = 20,
        name = "소고기뭇국",
        ingredients = listOf(
            IngredientAmount("소고기", "150g"),
            IngredientAmount("무", "150g"),
            IngredientAmount("대파", "약간"),
            IngredientAmount("마늘", "2쪽"),
            IngredientAmount("참기름", "1큰술")
        ),
        steps = listOf(
            "소고기와 무, 대파를 준비해요.",
            "소고기와 무를 냄비에 넣고 볶아요.",
            "물을 넣고 끓인 뒤 마늘과 대파를 넣어 완성해요."
        )
    ),
    Recipe(
        id = 21,
        name = "잡채",
        ingredients = listOf(
            IngredientAmount("당면", "100g"),
            IngredientAmount("시금치", "한 줌"),
            IngredientAmount("당근", "1/2개"),
            IngredientAmount("양파", "1/2개"),
            IngredientAmount("버섯", "한 줌"),
            IngredientAmount("돼지고기", "100g"),
            IngredientAmount("참기름", "1큰술")
        ),
        steps = listOf(
            "당면과 채소, 돼지고기를 준비해요.",
            "각 재료를 충분히 익혀요.",
            "당면과 재료를 함께 섞고 참기름을 넣어 완성해요."
        )
    ),
    Recipe(
        id = 22,
        name = "치즈라면",
        ingredients = listOf(
            IngredientAmount("라면", "1개"),
            IngredientAmount("치즈", "1장"),
            IngredientAmount("달걀", "1개"),
            IngredientAmount("대파", "약간")
        ),
        steps = listOf(
            "대파를 준비해요.",
            "라면을 조리법에 따라 끓여요.",
            "달걀과 치즈, 대파를 넣어 완성해요."
        )
    )
)

fun findRecipe(id: Int): Recipe? = RECIPES.find { it.id == id }
    // TODO: 닭갈비, 김치찌개, 계란찜, 불고기, 제육볶음, 비빔밥, 삼겹살구이, 두부김치, 김치찜
    //       id는 2, 3, 4 ... 순서대로

