package com.example.testdagger

import androidx.lifecycle.ViewModel
import com.example.testdagger.presentation.MainPresenter
import com.example.testdagger.presentation.repository.MainRepository
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TestClass @Inject constructor(
    private val mainPresenter: MainPresenter
): ViewModel() {


}