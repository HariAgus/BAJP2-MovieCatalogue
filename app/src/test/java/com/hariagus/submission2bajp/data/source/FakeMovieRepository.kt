package com.hariagus.submission2bajp.data.source

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.hariagus.submission2bajp.data.source.local.entity.MovieEntity
import com.hariagus.submission2bajp.data.source.local.entity.TvShowEntity
import com.hariagus.submission2bajp.data.source.remote.RemoteDataSource
import com.hariagus.submission2bajp.data.source.remote.response.MovieItem
import com.hariagus.submission2bajp.data.source.remote.response.TvShowItem

class FakeMovieRepository(private val remoteDataSource: RemoteDataSource) : MovieDataSource {

    override fun getAllMovies(): LiveData<List<MovieEntity>> {
        val movieResult = MutableLiveData<List<MovieEntity>>()
        remoteDataSource.getMovies(object : RemoteDataSource.LoadMovieCallback {
            override fun onAllMoviesReceived(movieItemResponse: List<MovieItem>?) {
                val movieList = ArrayList<MovieEntity>()
                if (movieItemResponse != null) {
                    for (movie in movieItemResponse) {
                        with(movie) {
                            movieList.add(
                                MovieEntity(
                                    overview,
                                    originalLanguage,
                                    originalTitle,
                                    title,
                                    posterPath,
                                    backdropPath,
                                    releaseDate,
                                    popularity,
                                    voteAverage,
                                    id,
                                    voteCount
                                )
                            )
                        }
                    }
                }
                movieResult.postValue(movieList)
            }
        })
        return movieResult
    }

    override fun getMovieById(movieId: String): LiveData<MovieEntity> {
        val movieResult = MutableLiveData<MovieEntity>()
        remoteDataSource.getMovies(object : RemoteDataSource.LoadMovieCallback {
            override fun onAllMoviesReceived(movieItemResponse: List<MovieItem>?) {
                lateinit var movie: MovieEntity
                if (movieItemResponse != null) {
                    for (item in movieItemResponse) {
                        if (movieId == item.id.toString()) {
                            with(item) {
                                movie = MovieEntity(
                                    overview,
                                    originalLanguage,
                                    originalTitle,
                                    title,
                                    posterPath,
                                    backdropPath,
                                    releaseDate,
                                    popularity,
                                    voteAverage,
                                    id,
                                    voteCount
                                )
                            }
                        }
                    }
                }
                movieResult.postValue(movie)
            }
        })
        return movieResult
    }

    override fun getAllTvShow(): LiveData<List<TvShowEntity>> {
        val tvShowResult = MutableLiveData<List<TvShowEntity>>()
        remoteDataSource.getTvShow(object : RemoteDataSource.LoadTvShowCallback {
            override fun onAllTvShowReceived(tvTvShowItemResponse: List<TvShowItem>?) {
                val tvShowList = ArrayList<TvShowEntity>()
                if (tvTvShowItemResponse != null) {
                    for (tvShow in tvTvShowItemResponse) {
                        with(tvShow) {
                            tvShowList.add(
                                TvShowEntity(
                                firstAirDate,
                                overview,
                                originalLanguage,
                                posterPath,
                                backdropPath,
                                popularity,
                                voteAverage,
                                id,
                                name,
                                voteCount,
                            )
                            )
                        }
                    }
                }
                tvShowResult.postValue(tvShowList)
            }
        })
        return tvShowResult
    }

    override fun getTvShowById(tvShowId: String): LiveData<TvShowEntity> {
        val tvShowResult = MutableLiveData<TvShowEntity>()
        remoteDataSource.getTvShow(object : RemoteDataSource.LoadTvShowCallback {
            override fun onAllTvShowReceived(tvTvShowItemResponse: List<TvShowItem>?) {
                lateinit var tvShow: TvShowEntity
                if (tvTvShowItemResponse != null) {
                    for (item in tvTvShowItemResponse) {
                        if (tvShowId == item.id.toString()) {
                            with(item) {
                                tvShow = TvShowEntity(
                                    firstAirDate,
                                    overview,
                                    originalLanguage,
                                    posterPath,
                                    backdropPath,
                                    popularity,
                                    voteAverage,
                                    id,
                                    name,
                                    voteCount
                                )
                            }
                        }
                    }
                }
                tvShowResult.postValue(tvShow)
            }
        })
        return tvShowResult
    }
}