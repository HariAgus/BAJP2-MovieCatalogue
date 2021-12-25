package com.hariagus.submission2bajp.ui.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.hariagus.submission2bajp.data.source.MovieRepository
import com.hariagus.submission2bajp.data.source.local.entity.TvShowEntity

class TvShowViewModel(private val movieRepository: MovieRepository): ViewModel() {

    fun getTvShow(): LiveData<List<TvShowEntity>> = movieRepository.getAllTvShow()

}