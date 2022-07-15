package com.example.testdagger

import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.testdagger.data.InternetConnectionReceiver
import com.example.testdagger.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    lateinit var receiver: InternetConnectionReceiver
    private lateinit var navController: NavController
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        receiver = InternetConnectionReceiver()
        IntentFilter(Intent.ACTION_POWER_CONNECTED).also {
            registerReceiver(receiver, it)
        }
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragment_container_view) as NavHostFragment
        navController = navHostFragment.findNavController()
        val bottomNavigationView = binding.bnvNavigation
        bottomNavigationView.setupWithNavController(navController)

    }



    override fun onStop() {
        super.onStop()
        unregisterReceiver(receiver)
    }
}