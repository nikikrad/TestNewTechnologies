package com.example.testdagger.presentation

import android.util.Log
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.arellomobile.mvp.MvpView
import com.example.testdagger.data.dataclass.ResponseTranslate
import com.example.testdagger.presentation.repository.MainRepository
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@InjectViewState
class MainPresenter @Inject constructor(
    private val mainRepository: MainRepository
): MvpPresenter<MvpView>() {

    private lateinit var q: String
    private lateinit var target: String

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        getTranslatedText(q, target)
    }

    fun getTranslatedText(q: String, target: String): Observable<ResponseTranslate> {
        this.q = q
        this.target = target
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