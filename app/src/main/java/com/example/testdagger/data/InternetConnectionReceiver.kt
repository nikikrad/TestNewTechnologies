package com.example.testdagger.data

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class InternetConnectionReceiver(): BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        val isConnected = intent?.getBooleanExtra("state", false)
        if (!isConnected!!){
            Toast.makeText(context, "Connection is enable!", Toast.LENGTH_SHORT).show()
        }else Toast.makeText(context, "Connection is disable!", Toast.LENGTH_SHORT).show()

    }
}