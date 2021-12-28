package com.hariagus.submission2bajp.ui.home

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import androidx.appcompat.app.AppCompatActivity
import com.hariagus.submission2bajp.databinding.ActivityHomeBinding
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewPagerAdapter = HomeViewPagerAdapter(this, supportFragmentManager)
        binding.homeViewPager.adapter = viewPagerAdapter
        binding.tabLayoutHome.setupWithViewPager(homeViewPager)

        binding.ivChangeLanguage.setOnClickListener {
            val intent = Intent(Settings.ACTION_LOCALE_SETTINGS)
            startActivity(intent)
        }
    }
}