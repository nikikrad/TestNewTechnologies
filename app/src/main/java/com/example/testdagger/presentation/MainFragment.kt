package com.example.testdagger.presentation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.example.testdagger.databinding.FragmentMainBinding
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainFragment : Fragment() {

    lateinit var binding: FragmentMainBinding
    val mainPresenter = MainPresenter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val arrayLanguage: MutableList<String> = emptyList<String>().toMutableList()
        arrayLanguage.add("ru")
        arrayLanguage.add("en")
        binding.btnSendText.setOnClickListener {
            mainPresenter.getTranslatedText(binding.etLineForTranslate.text, arrayLanguage[binding.spinner.selectedItemPosition])
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ translatedText ->
                    binding.tvTranslatedText.text = translatedText
                    binding.progressBar.isVisible = translatedText.isEmpty()
                }, {
                    Log.e("KEK", it.localizedMessage!!)
                })
        }
    }


}