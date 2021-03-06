package com.example.testdagger.presentation

import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.text.Editable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.testdagger.databinding.FragmentMainBinding
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.*
import javax.inject.Inject

@AndroidEntryPoint
class MainFragment : Fragment() {

    lateinit var binding: FragmentMainBinding
    @Inject
    lateinit var mainPresenter: MainPresenter
    lateinit var textToSpeech: TextToSpeech

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
        arrayLanguage.add("RU")
        arrayLanguage.add("EN")
        arrayLanguage.add("JA")
        binding.btnSendText.setOnClickListener {
            mainPresenter.getTranslatedText(binding.etLineForTranslate.text.toString(), arrayLanguage[binding.spinner.selectedItemPosition])
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ responseTranslate ->
                    binding.tvTranslatedText.text = responseTranslate.data.translations[0].translatedText
                    binding.tvDetectedLanguage.text = "Detected language is " + responseTranslate.data.translations[0].detectedSourceLanguage
                }, {
                    Log.e("KEK", it.localizedMessage!!)
                })
        }
        binding.btnVoiceText.setOnClickListener{
            textToSpeech = TextToSpeech(context, TextToSpeech.OnInitListener {
                if(it == TextToSpeech.SUCCESS){
                    when(binding.spinner.selectedItemPosition){
                        1 -> textToSpeech.language = Locale.UK
                        2 -> textToSpeech.language = Locale.US
                        3 -> textToSpeech.language = Locale.JAPAN
                    }
                    textToSpeech.setSpeechRate(0.80f)
                    textToSpeech.speak(binding.tvTranslatedText.text.toString(), TextToSpeech.QUEUE_ADD, null)
                }
            })
        }
    }




}