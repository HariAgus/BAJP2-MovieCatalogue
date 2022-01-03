package com.hariagus.submission2bajp.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.hariagus.submission2bajp.data.source.MovieRepository
import com.hariagus.submission2bajp.data.source.local.entity.TrendingEntity

class HomeViewModel(private val repository: MovieRepository) : ViewModel() {

    fun getTrending(): LiveData<List<TrendingEntity>> = repository.getTrending()

}