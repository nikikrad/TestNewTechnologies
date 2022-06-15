package com.example.testdagger

import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.testdagger.data.InternetConnectionReceiver

class MainActivity : AppCompatActivity() {

    lateinit var receiver: InternetConnectionReceiver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        receiver = InternetConnectionReceiver()
        IntentFilter(Intent.ACTION_POWER_CONNECTED).also {
            registerReceiver(receiver, it)
        }
    }

    override fun onStop() {
        super.onStop()
        unregisterReceiver(receiver)
    }
}