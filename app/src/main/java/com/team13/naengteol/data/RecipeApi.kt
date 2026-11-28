
package com.team13.naengteol.data

import com.team13.naengteol.BuildConfig
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.net.HttpURLConnection
import java.net.URL

object RecipeApi {

    private const val BASE_URL =
        "http://openapi.foodsafetykorea.go.kr/api"

    suspend fun getRecipes(): List<RecipeDto> =
        withContext(Dispatchers.IO) {

            val key = BuildConfig.FOOD_SAFETY_API_KEY

            require(key.isNotBlank()) {
                "API key is missing. Check local.properties."
            }

            val url = URL(
                "$BASE_URL/$key/COOKRCP01/json/1/1000"
            )

            val connection =
                url.openConnection() as HttpURLConnection

            try {
                connection.requestMethod = "GET"
                connection.connectTimeout = 15000
                connection.readTimeout = 15000

                val responseCode = connection.responseCode

                val response = (
                        if (responseCode in 200..299) {
                            connection.inputStream
                        } else {
                            connection.errorStream
                        }
                        ).bufferedReader().use { it.readText() }

                if (responseCode !in 200..299) {
                    throw Exception("HTTP error: $responseCode")
                }

                parseRecipes(response)

            } finally {
                connection.disconnect()
            }
        }

    private fun parseRecipes(
        response: String
    ): List<RecipeDto> {

        val root = JSONObject(response)
        val result = root.optJSONObject("COOKRCP01")
            ?: throw Exception("Invalid API response")

        val status = result.optJSONObject("RESULT")
        val code = status?.optString("CODE")

        if (code != null && code != "INFO-000") {
            val message = status.optString("MSG")
            throw Exception("$code: $message")
        }

        val rows = result.optJSONArray("row")
            ?: return emptyList()

        return buildList {

            for (i in 0 until rows.length()) {

                val item = rows.getJSONObject(i)

                val instructions = mutableListOf<String>()
                val instructionImages = mutableListOf<String>()

                for (step in 1..20) {

                    val number = step.toString()
                        .padStart(2, '0')

                    val instruction =
                        item.optString("MANUAL$number")
                            .trim()

                    val image =
                        item.optString("MANUAL_IMG$number")
                            .trim()

                    if (instruction.isNotBlank()) {
                        instructions.add(instruction)
                        instructionImages.add(image)
                    }
                }

                val recipe = RecipeDto(
                    id = item.optString("RCP_SEQ").toIntOrNull() ?: 0,
                    name = item.optString("RCP_NM"),
                    cookingMethod = item.optString("RCP_WAY2"),
                    category = item.optString("RCP_PAT2"),
                    ingredients = item.optString("RCP_PARTS_DTLS"),
                    imageUrl = item.optString("ATT_FILE_NO_MAIN"),
                    instructions = instructions,
                    instructionImages = instructionImages,
                    calories = item.optString("INFO_ENG"),
                    nutritionTip = item.optString("RCP_NA_TIP")
                )

                if (recipe.id != 0 && recipe.name.isNotBlank()) {
                    add(recipe)
                }
            }
        }
    }
}
