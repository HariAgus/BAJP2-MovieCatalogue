package com.hariagus.submission2bajp.di

import com.hariagus.submission2bajp.ui.detail.DetailViewModel
import com.hariagus.submission2bajp.ui.movie.MovieViewModel
import com.hariagus.submission2bajp.ui.tvshow.TvShowViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { MovieViewModel(get()) }
    viewModel { TvShowViewModel(get()) }
    viewModel { DetailViewModel(get()) }
}