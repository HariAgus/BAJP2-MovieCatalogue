package com.hariagus.submission2bajp.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.hariagus.submission2bajp.data.source.MovieRepository
import com.hariagus.submission2bajp.data.source.local.entity.MovieEntity
import com.hariagus.submission2bajp.data.source.local.entity.TvShowEntity

class DetailViewModel(private val movieRepository: MovieRepository) : ViewModel() {

    private lateinit var movieId: String
    private lateinit var tvShowId: String

    fun setSelectedMovie(movieId: String) {
        this.movieId = movieId
    }

    fun setSelectedTvShow(tvShowId: String) {
        this.tvShowId = tvShowId
    }

    fun getMovie(): LiveData<MovieEntity> = movieRepository.getMovieById(movieId)

    fun getTvShow(): LiveData<TvShowEntity> = movieRepository.getTvShowById(tvShowId)

}