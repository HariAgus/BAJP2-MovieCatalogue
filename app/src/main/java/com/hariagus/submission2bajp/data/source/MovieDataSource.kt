package com.hariagus.submission2bajp.data.source

import androidx.lifecycle.LiveData
import com.hariagus.submission2bajp.data.source.local.entity.MovieEntity
import com.hariagus.submission2bajp.data.source.local.entity.TrendingEntity
import com.hariagus.submission2bajp.data.source.local.entity.TvShowEntity
import com.hariagus.submission2bajp.data.source.remote.response.TrendingItem

interface MovieDataSource {

    fun getTrending(): LiveData<List<TrendingEntity>>

    fun getAllMovies(): LiveData<List<MovieEntity>>

    fun getMovieById(movieId: String): LiveData<MovieEntity>

    fun getAllTvShow(): LiveData<List<TvShowEntity>>

    fun getTvShowById(tvShowId: String): LiveData<TvShowEntity>

}