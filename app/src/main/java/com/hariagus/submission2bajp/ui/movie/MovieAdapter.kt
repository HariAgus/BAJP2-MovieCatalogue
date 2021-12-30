package com.hariagus.submission2bajp.ui.movie

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hariagus.submission2bajp.R
import com.hariagus.submission2bajp.data.source.local.entity.MovieEntity
import com.hariagus.submission2bajp.databinding.ItemListBinding
import com.hariagus.submission2bajp.ui.detail.DetailActivity
import com.hariagus.submission2bajp.ui.detail.DetailActivity.Companion.EXTRA_TYPE
import com.hariagus.submission2bajp.ui.detail.DetailActivity.Companion.ID_DATA
import com.hariagus.submission2bajp.ui.detail.TypeCatalogue
import com.hariagus.submission2bajp.utils.loadImageGlideAnim

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    private var listMovies = ArrayList<MovieEntity>()

    fun setMovies(movies: List<MovieEntity>?) {
        if (movies == null) return
        listMovies.apply {
            clear()
            addAll(movies)
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): MovieViewHolder {
        val itemListBinding = ItemListBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return MovieViewHolder(itemListBinding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(listMovies[position])
    }

    override fun getItemCount(): Int = listMovies.size

    class MovieViewHolder(private val binding: ItemListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movieEntity: MovieEntity) {
            with(binding) {
                tvTitle.text = movieEntity.title
                tvScore.text = movieEntity.voteAverage.toString()
                itemView.context.loadImageGlideAnim(
                    itemView.context.getString(R.string.url_poster, movieEntity.posterPath),
                    roundedPoster
                )
            }

            itemView.setOnClickListener {
                val intent = Intent(itemView.context, DetailActivity::class.java).apply {
                    putExtra(EXTRA_TYPE, TypeCatalogue.MOVIE.ordinal)
                    putExtra(ID_DATA, movieEntity.id)
                }
                itemView.context.startActivity(intent)
            }
        }
    }
}