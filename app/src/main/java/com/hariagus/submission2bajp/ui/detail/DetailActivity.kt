package com.hariagus.submission2bajp.ui.detail

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.hariagus.submission2bajp.R
import com.hariagus.submission2bajp.data.source.local.entity.MovieEntity
import com.hariagus.submission2bajp.data.source.local.entity.TvShowEntity
import com.hariagus.submission2bajp.databinding.ActivityDetailBinding
import com.hariagus.submission2bajp.utils.loadImageGlide
import com.hariagus.submission2bajp.viewmodel.ViewModelFactory

class DetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_TYPE = "type"
        const val ID_DATA = "id_data"
    }

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val type = intent.getIntExtra(EXTRA_TYPE, -1)
        val typeEnum: TypeDetail = TypeDetail.values()[type]
        val id = intent.getIntExtra(ID_DATA, -1)

        val factory = ViewModelFactory.getInstance(this)
        val viewModel = ViewModelProvider(this, factory)[DetailViewModel::class.java]

        binding.svLoadingDetail.visibility = View.VISIBLE
        binding.nestedScroll.visibility = View.GONE
        when (typeEnum) {
            TypeDetail.MOVIE -> {
                viewModel.setSelectedMovie(id.toString())
                viewModel.getMovie().observe(this, { movie ->
                    binding.svLoadingDetail.visibility = View.GONE
                    binding.nestedScroll.visibility = View.VISIBLE
                    loadDataMovie(movie)
                })
            }
            TypeDetail.TV_SHOW -> {
                viewModel.setSelectedTvShow(id.toString())
                viewModel.getTvShow().observe(this, { tvShow ->
                    binding.svLoadingDetail.visibility = View.GONE
                    binding.nestedScroll.visibility = View.VISIBLE
                    loadDataTvShow(tvShow)
                })
            }
        }

    }

    private fun loadDataMovie(movieEntity: MovieEntity) {
        with(binding) {
            tvTitleDetail.text = movieEntity.title
            tvLanguage.text = movieEntity.originalLanguage
            tvPopularity.text = movieEntity.popularity.toString()
            tvOverviewDetail.text = movieEntity.overview
            tvReleaseDate.text = movieEntity.releaseDate
            tvScoreDetail.text = movieEntity.voteAverage.toString()
            loadImageGlide(
                this@DetailActivity.getString(
                    R.string.url_poster, movieEntity.posterPath
                ),
                roundedPosterDetail
            )
            loadImageGlide(
                this@DetailActivity.getString(
                    R.string.url_poster, movieEntity.backdropPath
                ),
                posterBg
            )
        }
    }

    private fun loadDataTvShow(tvShowEntity: TvShowEntity) {
        with(binding) {
            tvTitleDetail.text = tvShowEntity.name
            tvLanguage.text = tvShowEntity.originalLanguage
            tvPopularity.text = tvShowEntity.popularity.toString()
            tvOverviewDetail.text = tvShowEntity.overview
            tvReleaseDate.text = tvShowEntity.firstAirDate
            tvScoreDetail.text = tvShowEntity.voteAverage.toString()
            loadImageGlide(
                this@DetailActivity.getString(
                    R.string.url_poster, tvShowEntity.posterPath
                ),
                roundedPosterDetail
            )
            loadImageGlide(
                this@DetailActivity.getString(
                    R.string.url_poster, tvShowEntity.backdropPath
                ),
                posterBg
            )
        }
    }
}