package com.hariagus.submission2bajp.di

import com.hariagus.submission2bajp.data.source.MovieRepository
import org.koin.dsl.module

val repositoryModule = module {
    single { MovieRepository(get()) }
}