package com.hariagus.submission2bajp.ui.movie

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.hariagus.submission2bajp.R
import com.hariagus.submission2bajp.data.source.local.entity.MovieEntity
import com.hariagus.submission2bajp.databinding.ItemListBinding
import com.hariagus.submission2bajp.utils.CatalogueDiffUtil
import com.hariagus.submission2bajp.utils.loadImageGlideAnim

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    private var listMovies = emptyList<MovieEntity>()
    var onClickItem: ((MovieEntity) -> Unit)? = null

    fun setMovies(movies: List<MovieEntity>) {
        val movieDiffUtil = CatalogueDiffUtil(listMovies, movies)
        val diffUtilResult = DiffUtil.calculateDiff(movieDiffUtil)
        listMovies = movies
        diffUtilResult.dispatchUpdatesTo(this)
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

    inner class MovieViewHolder(private val binding: ItemListBinding) :
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
        }

        init {
            binding.root.setOnClickListener {
                onClickItem?.invoke(listMovies[adapterPosition])
            }
        }

    }

}