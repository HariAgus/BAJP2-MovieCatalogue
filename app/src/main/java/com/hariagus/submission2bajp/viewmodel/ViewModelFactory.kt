package com.hariagus.submission2bajp.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.hariagus.submission2bajp.data.source.MovieRepository
import com.hariagus.submission2bajp.di.Injection
import com.hariagus.submission2bajp.ui.detail.DetailViewModel
import com.hariagus.submission2bajp.ui.movie.MovieViewModel
import com.hariagus.submission2bajp.ui.tvshow.TvShowViewModel

class ViewModelFactory private constructor(
    private val movieRepository: MovieRepository
) : ViewModelProvider.NewInstanceFactory() {

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(context: Context): ViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: ViewModelFactory(Injection.provideRepository(context))
            }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(MovieViewModel::class.java) -> {
                MovieViewModel(movieRepository) as T
            }
            modelClass.isAssignableFrom(TvShowViewModel::class.java) -> {
                TvShowViewModel(movieRepository) as T
            }
            modelClass.isAssignableFrom(DetailViewModel::class.java) -> {
                DetailViewModel(movieRepository) as T
            }
            else -> throw Throwable("Unknown ViewModel class : " + modelClass.name)
        }
    }

}