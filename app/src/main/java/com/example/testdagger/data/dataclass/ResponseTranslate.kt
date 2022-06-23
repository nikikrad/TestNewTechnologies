package com.example.testdagger.data.dataclass

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ResponseTranslate(
    @Json(name = "data")
    val data: Data
){
    data class Data(
        @Json(name = "translation")
        val translation: translatedText
    )

    data class translatedText(
        @Json(name = "translatedText")
        val translatedText: String = ""
    )
}

