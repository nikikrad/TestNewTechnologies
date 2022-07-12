package com.example.testdagger.domain

import com.example.testdagger.data.dataclass.ResponseTranslate
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @POST("v2?key=AIzaSyDvJXHnMw6UYxwSHNkQ_m72JsohvmI8mLQ&q=Hello my name nikita&target=ru")
    fun sendRequest(): Call<ResponseTranslate>

    @GET("v2?key=AIzaSyDvJXHnMw6UYxwSHNkQ_m72JsohvmI8mLQ")
    fun sendGetRequest(
        @Query("q") q: String,
        @Query("target") target: String
    ):Observable<ResponseTranslate>

}