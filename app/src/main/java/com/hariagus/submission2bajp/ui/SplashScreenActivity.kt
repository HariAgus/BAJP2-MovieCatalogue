package com.hariagus.submission2bajp.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.hariagus.submission2bajp.R
import com.hariagus.submission2bajp.ui.home.HomeActivity
import com.hariagus.submission2bajp.utils.Const.Companion.DELAY_MOVE

class SplashScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        Handler(mainLooper).postDelayed({
            startActivity(Intent(this, HomeActivity::class.java))
            finish()
        }, DELAY_MOVE)
    }
}