package com.hariagus.submission2bajp.ui.tvshow

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.hariagus.submission2bajp.R
import com.hariagus.submission2bajp.data.source.local.entity.TvShowEntity
import com.hariagus.submission2bajp.databinding.ItemListBinding
import com.hariagus.submission2bajp.utils.CatalogueDiffUtil
import com.hariagus.submission2bajp.utils.loadImageGlideAnim

class TvShowAdapter : RecyclerView.Adapter<TvShowAdapter.TvShowViewHolder>() {

    private var listTvShow = emptyList<TvShowEntity>()
    var onClickItem: ((TvShowEntity) -> Unit)? = null

    fun setTvShow(tvShow: List<TvShowEntity>) {
        val tvShowDiffUtil = CatalogueDiffUtil(listTvShow, tvShow)
        val diffUtilResult = DiffUtil.calculateDiff(tvShowDiffUtil)
        listTvShow = tvShow
        diffUtilResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): TvShowAdapter.TvShowViewHolder {
        val itemListBinding = ItemListBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return TvShowViewHolder(itemListBinding)
    }

    override fun onBindViewHolder(holder: TvShowAdapter.TvShowViewHolder, position: Int) {
        holder.bind(listTvShow[position])
    }

    override fun getItemCount(): Int = listTvShow.size

    inner class TvShowViewHolder(private val binding: ItemListBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(tvShow: TvShowEntity) {
            with(binding) {
                tvTitle.text = tvShow.name
                tvScore.text = tvShow.voteAverage.toString()
                itemView.context.loadImageGlideAnim(
                    itemView.context.getString(R.string.url_poster, tvShow.posterPath),
                    roundedPoster
                )
            }
        }

        init {
            binding.root.setOnClickListener {
                onClickItem?.invoke(listTvShow[adapterPosition])
            }
        }
    }
}