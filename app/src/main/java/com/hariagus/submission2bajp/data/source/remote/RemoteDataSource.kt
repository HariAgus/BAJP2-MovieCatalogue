package com.hariagus.submission2bajp.data.source.remote

import android.util.Log
import com.hariagus.submission2bajp.data.source.remote.response.*
import com.hariagus.submission2bajp.network.NetworkClient
import com.hariagus.submission2bajp.utils.EspressoIdlingResource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSource {

    companion object {
        private val TAG: String = RemoteDataSource::class.java.simpleName
    }

    fun getTrending(callback: LoadTrendingCallback) {
        EspressoIdlingResource.increment()
        NetworkClient.getApiService().getTrendingCatalogue()
            .enqueue(object : Callback<TrendingResponse> {
                override fun onResponse(
                    call: Call<TrendingResponse>,
                    response: Response<TrendingResponse>
                ) {
                    callback.onAllTrendingReceived(response.body()?.results)
                    EspressoIdlingResource.decrement()
                }

                override fun onFailure(call: Call<TrendingResponse>, t: Throwable) {
                    Log.e(TAG, "onFailure: ${t.message.toString()}")
                    EspressoIdlingResource.decrement()
                }
            })
    }

    fun getMovies(callback: LoadMovieCallback) {
        EspressoIdlingResource.increment()
        NetworkClient.getApiService().getMovies().enqueue(object : Callback<MovieResponse> {
            override fun onResponse(
                call: Call<MovieResponse>,
                response: Response<MovieResponse>
            ) {
                callback.onAllMoviesReceived(response.body()?.results)
                EspressoIdlingResource.decrement()
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message.toString()}")
                EspressoIdlingResource.decrement()
            }
        })
    }

    fun getTvShow(callback: LoadTvShowCallback) {
        EspressoIdlingResource.increment()
        NetworkClient.getApiService().getTvShow().enqueue(object : Callback<TvShowResponse> {
            override fun onResponse(
                call: Call<TvShowResponse>,
                response: Response<TvShowResponse>
            ) {
                callback.onAllTvShowReceived(response.body()?.results)
                EspressoIdlingResource.decrement()
            }

            override fun onFailure(call: Call<TvShowResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message.toString()}")
                EspressoIdlingResource.decrement()
            }
        })
    }

    interface LoadTrendingCallback {
        fun onAllTrendingReceived(trendingResponse: List<TrendingItem>?)
    }

    interface LoadMovieCallback {
        fun onAllMoviesReceived(movieItemResponse: List<MovieItem>?)
    }

    interface LoadTvShowCallback {
        fun onAllTvShowReceived(tvTvShowItemResponse: List<TvShowItem>?)
    }

}