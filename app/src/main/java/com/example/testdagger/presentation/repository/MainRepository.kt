package com.example.testdagger.presentation.repository

import android.util.Log
import com.example.testdagger.data.dataclass.ResponseTranslate
import com.example.testdagger.domain.ApiService
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MainRepository @Inject constructor(private val apiService: ApiService) {

    fun getTranslate(q: String, target: String): Observable<ResponseTranslate> {
        return Observable.create{ observable ->
            apiService.sendGetRequest(q, target)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ response ->
                    observable.onNext(response)
                }, { throwable ->
                    Log.e("ERROR", throwable.toString())
                })
        }
    }

}