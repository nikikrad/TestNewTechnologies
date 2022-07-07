package com.example.testdagger.data.dataclass

data class ResponseTranslate(
    val data: Data
){
    data class Data(
        val translations: List<TranslatedText>
    )

    data class TranslatedText(
        val translatedText: String = "",
        val detectedSourceLanguage: String = ""
    )
}

