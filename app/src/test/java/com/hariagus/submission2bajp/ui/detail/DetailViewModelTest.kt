package com.hariagus.submission2bajp.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.hariagus.submission2bajp.data.source.MovieRepository
import com.hariagus.submission2bajp.data.source.local.entity.MovieEntity
import com.hariagus.submission2bajp.data.source.local.entity.TvShowEntity
import com.hariagus.submission2bajp.utils.DataDummyMovies
import com.hariagus.submission2bajp.utils.DataDummyTvShow
import com.nhaarman.mockitokotlin2.verify
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DetailViewModelTest {

    private lateinit var detailViewModel: DetailViewModel

    private val dataDummyMovie = DataDummyMovies.generateDummyMovies()[0]
    private val movieId = dataDummyMovie.id.toString()
    private val dataDummyTvShow =  DataDummyTvShow.generateDummyTvShow()[0]
    private val tvShowId = dataDummyTvShow.id.toString()

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieRepository: MovieRepository

    @Mock
    private lateinit var movieObserver: Observer<MovieEntity>

    @Mock
    private lateinit var tvShowObserver: Observer<TvShowEntity>

    @Before
    fun setup() {
        detailViewModel = DetailViewModel(movieRepository)
        detailViewModel.setSelectedMovie(movieId)
        detailViewModel.setSelectedTvShow(tvShowId)
    }

    @Test
    fun getDetailMovie() {
        val movie = MutableLiveData<MovieEntity>()
        movie.value = dataDummyMovie

        `when`(movieRepository.getMovieById(movieId)).thenReturn(movie)
        val movieEntity = detailViewModel.getMovie().value as MovieEntity
        verify(movieRepository).getMovieById(movieId)

        assertNotNull(movieEntity)
        assertEquals(dataDummyMovie.overview, movieEntity.overview)
        assertEquals(dataDummyMovie.originalLanguage, movieEntity.originalLanguage)
        assertEquals(dataDummyMovie.originalTitle, movieEntity.originalTitle)
        assertEquals(dataDummyMovie.title, movieEntity.title)
        assertEquals(dataDummyMovie.posterPath, movieEntity.posterPath)
        assertEquals(dataDummyMovie.backdropPath, movieEntity.backdropPath)
        assertEquals(dataDummyMovie.releaseDate, movieEntity.releaseDate)
        assertEquals(dataDummyMovie.popularity, movieEntity.popularity, dataDummyMovie.popularity)
        assertEquals(dataDummyMovie.voteAverage, movieEntity.voteAverage, dataDummyMovie.voteAverage)
        assertEquals(dataDummyMovie.id, movieEntity.id)
        assertEquals(dataDummyMovie.voteCount, movieEntity.voteCount)

        detailViewModel.getMovie().observeForever(movieObserver)
        verify(movieObserver).onChanged(dataDummyMovie)
    }

    @Test
    fun getDetailTvShow() {
        val tvShow = MutableLiveData<TvShowEntity>()
        tvShow.value = dataDummyTvShow

        `when`(movieRepository.getTvShowById(tvShowId)).thenReturn(tvShow)
        val tvShowEntity = detailViewModel.getTvShow().value as TvShowEntity
        verify(movieRepository).getTvShowById(tvShowId)

        assertNotNull(tvShowEntity)
        assertEquals(dataDummyTvShow.firstAirDate, tvShowEntity.firstAirDate)
        assertEquals(dataDummyTvShow.overview, tvShowEntity.overview)
        assertEquals(dataDummyTvShow.originalLanguage, tvShowEntity.originalLanguage)
        assertEquals(dataDummyTvShow.posterPath, tvShowEntity.posterPath)
        assertEquals(dataDummyTvShow.backdropPath, tvShowEntity.backdropPath)
        assertEquals(dataDummyTvShow.popularity, tvShowEntity.popularity, tvShowEntity.popularity)
        assertEquals(dataDummyTvShow.voteAverage, tvShowEntity.voteAverage, tvShowEntity.voteAverage)
        assertEquals(dataDummyTvShow.id, tvShowEntity.id)
        assertEquals(dataDummyTvShow.voteCount, tvShowEntity.voteCount)

        detailViewModel.getTvShow().observeForever(tvShowObserver)
        verify(tvShowObserver).onChanged(dataDummyTvShow)
    }

}