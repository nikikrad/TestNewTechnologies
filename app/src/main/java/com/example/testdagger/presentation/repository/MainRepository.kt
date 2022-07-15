package com.example.testdagger.presentation.repository

import android.util.Log
import com.example.testdagger.domain.ApiService
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainRepository(private val apiService: ApiService) {

    fun getTranslate(q: String, target: String): Observable<String> {
        return Observable.create{ observable ->
            apiService.sendGetRequest(q, target)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ response ->
                    observable.onNext(response.data.translations[0].translatedText)
                }, { throwable ->
                    Log.e("ERROR", throwable.toString())
                })
        }
    }

}