package com.hariagus.submission2bajp.di

import android.content.Context
import com.hariagus.submission2bajp.data.source.MovieRepository
import com.hariagus.submission2bajp.data.source.remote.RemoteDataSource

object Injection {

    fun provideRepository(context: Context): MovieRepository {
        val remoteRepository = RemoteDataSource.getInstance()
        return MovieRepository.getInstance(remoteRepository)

    }
}