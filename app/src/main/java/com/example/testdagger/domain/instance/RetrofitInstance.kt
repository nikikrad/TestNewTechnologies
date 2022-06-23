package com.example.testdagger.domain.instance

import com.example.testdagger.data.dataclass.ResponseTranslate
import com.google.gson.annotations.JsonAdapter
import com.squareup.moshi.Moshi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


internal class RetrofitInstance {

    companion object{
        private const val URL = "https://translation.googleapis.com/language/translate/"

        fun getRetrofitInstance(): Retrofit {
            return Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }
}