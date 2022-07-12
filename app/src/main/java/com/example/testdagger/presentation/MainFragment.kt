package com.example.testdagger.presentation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
        binding.btnSendText.setOnClickListener {
            val q = binding.etLineForTranslate.text
            mainPresenter.getTranslatedText(q)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    binding.tvTranslatedText.text = it
                    Log.e("KEK", it)
                }, {
                    Log.e("KEK", it.localizedMessage!!)
                })
        }
    }


}