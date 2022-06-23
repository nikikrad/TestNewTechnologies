package com.example.testdagger.domain

import com.example.testdagger.data.ApiKey.GoogleTranslationApiKey
import com.example.testdagger.data.dataclass.ResponseTranslate
import retrofit2.Response
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {
    @POST("v2?key=AIzaSyDvJXHnMw6UYxwSHNkQ_m72JsohvmI8mLQ&q={text}&target=ru")
    fun sendRequest(
        @Path("text") text: String
    ): Response<ResponseTranslate>
}