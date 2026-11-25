# 냉털 (Naengteol)

냉장고에 있는 재료를 고르면 만들 수 있는 요리를 추천해 주는 Android 앱입니다.
프로그램설계방법론 13팀 기말 프로젝트 (2026)

## 주요 기능
1. **재료 선택**: 정해진 재료 목록에서 가지고 있는 재료를 체크
2. **요리 추천**: 선택한 재료가 많이 들어가는 요리부터 보여주고, 부족한 재료를 안내
3. **레시피 상세**: 필요한 재료와 분량, 조리 순서를 보여줌

## 개발 환경
- Android Studio, Kotlin, Jetpack Compose
- compileSdk 37 / minSdk 26

## 실행 방법
1. Android Studio → **Clone Repository** → 이 저장소 주소 입력
2. SDK 37 설치 안내가 나오면 설치
3. ▶ Run

## 폴더 구조와 담당
```
app/src/main/java/com/team13/naengteol/
├─ MainActivity.kt            화면 연결            윤정민
├─ data/
│  ├─ Recipe.kt               공통 데이터 형식      윤정민
│  └─ RecipeData.kt           요리·재료 데이터      빌구대이
├─ logic/
│  └─ Recommender.kt          추천 규칙            뭉흐오르길우수흐자르갈
└─ ui/
   ├─ theme/                  색상·글꼴 테마        윤정민
   ├─ IngredientScreen.kt     재료 선택 화면        심여진
   ├─ RecommendationScreen.kt 추천 메뉴 화면        뭉흐오르길우수흐자르갈
   └─ RecipeDetailScreen.kt   레시피 상세 화면      빌구대이
```

## 협업 규칙
- main 브랜치에는 직접 push할 수 없습니다. 각자 `feature/...` 브랜치에서 작업한 뒤 Pull Request를 올립니다.
- Pull Request는 팀원 2명의 승인이 있어야 main에 합쳐집니다.
- 자기 담당 파일만 수정하고, 공통 파일(MainActivity, Recipe.kt, build.gradle)은 1번 담당에게 요청합니다.

## 팀원
윤정민 · 심여진 · 뭉흐오르길우수흐자르갈 · 빌구대이
