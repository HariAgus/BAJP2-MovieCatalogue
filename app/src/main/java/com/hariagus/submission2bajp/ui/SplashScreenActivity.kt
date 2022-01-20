package com.hariagus.submission2bajp.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import androidx.appcompat.app.AppCompatActivity
import com.example.awesomedialog.AwesomeDialog
import com.example.awesomedialog.icon
import com.example.awesomedialog.onPositive
import com.example.awesomedialog.title
import com.hariagus.submission2bajp.R
import com.hariagus.submission2bajp.databinding.ActivitySplashScreenBinding
import com.hariagus.submission2bajp.ui.home.HomeActivity
import com.hariagus.submission2bajp.utils.NetworkHelper
import com.hariagus.submission2bajp.utils.startActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@SuppressLint("CustomSplashScreen")
class SplashScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        CoroutineScope(Main).launch {
            checkNetwork()
        }

    }

    private suspend fun checkNetwork() {
        delay(2000L)

        val statusNetwork = NetworkHelper.isNetworkConnected(this)
        if (statusNetwork) {
            startActivity<HomeActivity>()
            finish()
        } else {
            withContext(Main) {
                showToast()
            }
        }
    }

    private fun showToast() {
        AwesomeDialog.build(this)
            .title(getString(R.string.no_internet))
            .icon(R.drawable.ic_congrts)
            .onPositive(getString(R.string.go_to_setting)) {
                val intent = Intent(Settings.ACTION_NETWORK_OPERATOR_SETTINGS)
                startActivity(intent)
            }

    }

    override fun onRestart() {
        super.onRestart()
        CoroutineScope(Main).launch {
            checkNetwork()
        }
    }

}