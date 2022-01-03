package com.hariagus.submission2bajp.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.hariagus.submission2bajp.databinding.ActivitySplashScreenBinding
import com.hariagus.submission2bajp.ui.home.HomeActivity
import com.hariagus.submission2bajp.utils.Const.DELAY_SPLASH_SCREEN
import com.hariagus.submission2bajp.utils.startActivity

@SuppressLint("CustomSplashScreen")
class SplashScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Handler(mainLooper).postDelayed({
            startActivity<HomeActivity>()
            finish()
        }, DELAY_SPLASH_SCREEN)
    }

}