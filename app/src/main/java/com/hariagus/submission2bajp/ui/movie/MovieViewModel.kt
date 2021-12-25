package com.hariagus.submission2bajp.ui.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.hariagus.submission2bajp.data.source.MovieRepository
import com.hariagus.submission2bajp.data.source.local.entity.MovieEntity

class MovieViewModel(private val movieRepository: MovieRepository) : ViewModel() {

    fun getMovies(): LiveData<List<MovieEntity>> = movieRepository.getAllMovies()

}