package com.hariagus.submission2bajp.di

import com.hariagus.submission2bajp.data.source.remote.RemoteDataSource
import com.hariagus.submission2bajp.network.NetworkClient
import org.koin.dsl.module

val appModule = module {
    single { NetworkClient.getApiService() }
    single { RemoteDataSource() }
}