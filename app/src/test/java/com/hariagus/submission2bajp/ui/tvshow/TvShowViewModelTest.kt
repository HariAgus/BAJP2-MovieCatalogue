package com.hariagus.submission2bajp.ui.tvshow

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.hariagus.submission2bajp.data.source.MovieRepository
import com.hariagus.submission2bajp.data.source.local.entity.TvShowEntity
import com.hariagus.submission2bajp.utils.DataDummyTvShow
import com.nhaarman.mockitokotlin2.verify
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class TvShowViewModelTest {

    private lateinit var tvShowViewModel: TvShowViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieRepository: MovieRepository

    @Mock
    private lateinit var observer: Observer<List<TvShowEntity>>

    @Before
    fun setup() {
        tvShowViewModel = TvShowViewModel(movieRepository)
    }

    @Test
    fun getTvShows() {
        val dataDummyTvShow = DataDummyTvShow.generateDummyTvShow()
        val tvShow = MutableLiveData<List<TvShowEntity>>()
        tvShow.value = dataDummyTvShow

        `when`(movieRepository.getAllTvShow()).thenReturn(tvShow)
        val tvShowEntity = tvShowViewModel.getTvShow().value
        verify(movieRepository).getAllTvShow()
        assertNotNull(tvShowEntity)
        assertEquals(2, tvShowEntity?.size)

        tvShowViewModel.getTvShow().observeForever(observer)
        verify(observer).onChanged(dataDummyTvShow)
    }

}