package com.hariagus.submission2bajp.ui.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.hariagus.submission2bajp.data.source.MovieRepository
import com.hariagus.submission2bajp.data.source.local.entity.MovieEntity
import com.hariagus.submission2bajp.utils.DataDummyMovies
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
class
MovieViewModelTest {

    private lateinit var movieViewModel: MovieViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieRepository: MovieRepository

    @Mock
    private lateinit var observer: Observer<List<MovieEntity>>

    @Before
    fun setup() {
        movieViewModel = MovieViewModel(movieRepository)
    }

    @Test
    fun getMovie() {
        val dataDummyMovie = DataDummyMovies.generateDummyMovies()
        val movie = MutableLiveData<List<MovieEntity>>()
        movie.value = dataDummyMovie

        `when`(movieRepository.getAllMovies()).thenReturn(movie)
        val movieEntity = movieViewModel.getMovies().value
        verify(movieRepository).getAllMovies()
        assertNotNull(movieEntity)
        assertEquals(2, movieEntity?.size)

        movieViewModel.getMovies().observeForever(observer)
        verify(observer).onChanged(dataDummyMovie)
    }

}