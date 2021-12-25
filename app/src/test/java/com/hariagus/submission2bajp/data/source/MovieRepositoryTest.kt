package com.hariagus.submission2bajp.data.source

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.hariagus.submission2bajp.data.source.remote.RemoteDataSource
import com.hariagus.submission2bajp.utils.DataDummyMovies
import com.hariagus.submission2bajp.utils.DataDummyTvShow
import com.hariagus.submission2bajp.utils.LiveDataTestUtil
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doAnswer
import com.nhaarman.mockitokotlin2.verify
import junit.framework.TestCase.*
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock

class MovieRepositoryTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remote = mock(RemoteDataSource::class.java)
    private val movieRepository = FakeMovieRepository(remote)
    private val movieResponse = DataDummyMovies.generateRemoteDummyMovies()
    private val movieId = movieResponse[0].id.toString()
    private val tvShowResponse = DataDummyTvShow.generateRemoteDummyTvShow()
    private val tvShowId = tvShowResponse[0].id.toString()

    @Test
    fun getAllMovies() {
        doAnswer { invocationOnMock ->
            (invocationOnMock.arguments[0] as RemoteDataSource.LoadMovieCallback)
                .onAllMoviesReceived(movieResponse)
            null
        }.`when`(remote).getMovies(any())
        val movieEntities = LiveDataTestUtil.getValue(movieRepository.getAllMovies())
        verify(remote).getMovies(any())
        assertNotNull(movieEntities)
        assertEquals(movieResponse.size.toLong(), movieEntities.size.toLong())
    }

    @Test
    fun getMovieById() {
        doAnswer { invocationOnMock ->
            (invocationOnMock.arguments[0] as RemoteDataSource.LoadMovieCallback)
                .onAllMoviesReceived(movieResponse)
            null
        }.`when`(remote).getMovies(any())
        val movieEntity = LiveDataTestUtil.getValue(movieRepository.getMovieById(movieId))
        val movieResponse = movieResponse[0]
        verify(remote).getMovies(any())
        assertNotNull(movieEntity)
        assertEquals(movieResponse.overview, movieEntity.overview)
        assertEquals(movieResponse.originalLanguage, movieEntity.originalLanguage)
        assertEquals(movieResponse.originalTitle, movieEntity.originalTitle)
        assertEquals(movieResponse.title, movieEntity.title)
        assertEquals(movieResponse.posterPath, movieEntity.posterPath)
        assertEquals(movieResponse.backdropPath, movieEntity.backdropPath)
        assertEquals(movieResponse.releaseDate, movieEntity.releaseDate)
        assertEquals(movieResponse.popularity, movieEntity.popularity)
        assertEquals(movieResponse.voteAverage, movieEntity.voteAverage)
        assertEquals(movieResponse.id, movieEntity.id)
        assertEquals(movieResponse.voteCount, movieEntity.voteCount)
    }

    @Test
    fun getAllTvShow() {
        doAnswer { invocationOnMock ->
            (invocationOnMock.arguments[0] as RemoteDataSource.LoadTvShowCallback)
                .onAllTvShowReceived(tvShowResponse)
            null
        }.`when`(remote).getTvShow(any())
        val tvShowEntities = LiveDataTestUtil.getValue(movieRepository.getAllTvShow())
        verify(remote).getTvShow(any())
        assertNotNull(tvShowEntities)
        assertEquals(tvShowResponse.size.toLong(), tvShowEntities.size.toLong())
    }

    @Test
    fun getTvShowId() {
        doAnswer { invocationOnMock ->
            (invocationOnMock.arguments[0] as RemoteDataSource.LoadTvShowCallback)
                .onAllTvShowReceived(tvShowResponse)
            null
        }.`when`(remote).getTvShow(any())
        val tvShowEntity = LiveDataTestUtil.getValue(movieRepository.getTvShowById(tvShowId))
        val tvShowResponse = tvShowResponse[0]
        verify(remote).getTvShow(any())
        assertNotNull(tvShowEntity)
        assertEquals(tvShowResponse.firstAirDate, tvShowResponse.firstAirDate)
        assertEquals(tvShowResponse.overview, tvShowResponse.overview)
        assertEquals(tvShowResponse.originalLanguage, tvShowResponse.originalLanguage)
        assertEquals(tvShowResponse.posterPath, tvShowResponse.posterPath)
        assertEquals(tvShowResponse.backdropPath, tvShowResponse.backdropPath)
        assertEquals(tvShowResponse.popularity, tvShowResponse.popularity)
        assertEquals(tvShowResponse.voteAverage, tvShowResponse.voteAverage)
        assertEquals(tvShowResponse.id, tvShowResponse.id)
        assertEquals(tvShowResponse.name, tvShowResponse.name)
        assertEquals(tvShowResponse.voteCount, tvShowResponse.voteCount)
    }

}