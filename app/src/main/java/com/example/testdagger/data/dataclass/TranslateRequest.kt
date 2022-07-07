package com.example.testdagger.data.dataclass

data class TranslateRequest(
    val q: String = "",
    val target: String = "ru"
)