package com.example.testdagger

import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.testdagger.data.InternetConnectionReceiver
import com.example.testdagger.domain.ApiService
import com.example.testdagger.domain.instance.RetrofitInstance

class MainActivity : AppCompatActivity() {

    lateinit var receiver: InternetConnectionReceiver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        receiver = InternetConnectionReceiver()
        IntentFilter(Intent.ACTION_POWER_CONNECTED).also {
            registerReceiver(receiver, it)
        }
        val retrofit = RetrofitInstance.getRetrofitInstance().create(ApiService::class.java)
        val request = retrofit.sendRequest("My Name Nikita")
        Thread.sleep(500)
        Log.e("KEK", request.message())

    }

    override fun onStop() {
        super.onStop()
        unregisterReceiver(receiver)
    }
}