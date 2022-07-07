package com.example.testdagger.presentation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.testdagger.databinding.FragmentMainBinding
import com.example.testdagger.domain.ApiService
import com.example.testdagger.domain.instance.RetrofitInstance
import kotlinx.coroutines.*

class MainFragment : Fragment() {

    lateinit var binding: FragmentMainBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        CoroutineScope(Dispatchers.IO).launch {
            val retrofit = RetrofitInstance.getRetrofitInstance().create(ApiService::class.java)
            val request = retrofit.sendRequest()
            val q = "Nikitosik Barbosik, Hello"
            val newRequest = retrofit.sendGetRequest(q, "ru")
            val bodyResponse = newRequest.body()
            Log.e("KEK", bodyResponse.toString())
        }
    }


}