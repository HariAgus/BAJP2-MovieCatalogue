package com.hariagus.submission2bajp.network

import com.hariagus.submission2bajp.BuildConfig
import com.hariagus.submission2bajp.data.source.remote.response.MovieResponse
import com.hariagus.submission2bajp.data.source.remote.response.TrendingResponse
import com.hariagus.submission2bajp.data.source.remote.response.TvShowResponse
import com.hariagus.submission2bajp.utils.Const.URL_MOVIE
import com.hariagus.submission2bajp.utils.Const.URL_TRENDING
import com.hariagus.submission2bajp.utils.Const.URL_TV_SHOW
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RestApi {

    @GET(URL_TRENDING)
    fun getTrendingCatalogue(
        @Query("api_key") apiKey: String = BuildConfig.API_KEY
    ): Call<TrendingResponse>

    @GET(URL_MOVIE)
    fun getMovies(
        @Query("api_key") apiKey: String = BuildConfig.API_KEY
    ): Call<MovieResponse>

    @GET(URL_TV_SHOW)
    fun getTvShow(
        @Query("api_key") apiKey: String = BuildConfig.API_KEY
    ): Call<TvShowResponse>

}