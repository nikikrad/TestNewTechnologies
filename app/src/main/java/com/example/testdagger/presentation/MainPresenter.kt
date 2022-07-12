package com.example.testdagger.presentation

import android.text.Editable
import android.util.Log
import com.example.testdagger.domain.ApiService
import com.example.testdagger.domain.instance.RetrofitInstance
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.*

class MainPresenter {

    fun getTranslatedText(q: Editable?): Observable<String> {
        return Observable.create { observable ->
            var kkk = ""
            val retrofit = RetrofitInstance.getRetrofitInstance().create(ApiService::class.java)
            retrofit.sendGetRequest(q.toString(), "ru")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ response ->
                    kkk = response.data.translations[0].translatedText
                    observable.onNext(kkk)
                }, { throwable ->
                    Log.e("ERROR", throwable.toString())
                })
        }
    }
}