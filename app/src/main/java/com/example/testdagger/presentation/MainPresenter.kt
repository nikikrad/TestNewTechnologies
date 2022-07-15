package com.example.testdagger.presentation

import android.app.Application
import android.util.Log
import com.example.testdagger.presentation.repository.MainRepository
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MainPresenter @Inject constructor(
    private val mainRepository: MainRepository
) {

    fun getTranslatedText(q: String, target: String): Observable<String> {
        return Observable.create { observable ->
            mainRepository.getTranslate(q, target)
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