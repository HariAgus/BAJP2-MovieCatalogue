package com.hariagus.submission2bajp.ui.home

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import androidx.appcompat.app.AppCompatActivity
import com.hariagus.submission2bajp.databinding.ActivityHomeBinding
import org.koin.androidx.viewmodel.ext.android.viewModel


class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private val viewModel: HomeViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.bannerTrending.startAutoCycle()

        val viewPagerAdapter = HomeViewPagerAdapter(this, supportFragmentManager)
        binding.apply {
            homeViewPager.adapter = viewPagerAdapter
            tabLayoutHome.setupWithViewPager(homeViewPager)
        }

        binding.ivChangeLanguage.setOnClickListener {
            val intent = Intent(Settings.ACTION_LOCALE_SETTINGS)
            startActivity(intent)
        }

        val bannerAdapter = BannerAdapter()

        viewModel.getTrending().observe(this, {
            bannerAdapter.setMovies(it)
            binding.bannerTrending.setSliderAdapter(bannerAdapter)
        })

    }
}