package com.hariagus.submission2bajp.ui.tvshow

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.hariagus.submission2bajp.R
import com.hariagus.submission2bajp.data.source.local.entity.TvShowEntity
import com.hariagus.submission2bajp.databinding.ItemListBinding
import com.hariagus.submission2bajp.ui.detail.DetailActivity
import com.hariagus.submission2bajp.ui.detail.DetailActivity.Companion.EXTRA_TYPE
import com.hariagus.submission2bajp.ui.detail.DetailActivity.Companion.ID_DATA
import com.hariagus.submission2bajp.ui.detail.TypeDetail

class TvShowAdapter : RecyclerView.Adapter<TvShowAdapter.TvShowViewHolder>() {

    private val listTvShow = ArrayList<TvShowEntity>()

    fun setTvShow(tvShow: List<TvShowEntity>?) {
        if (tvShow == null) return
        listTvShow.apply {
            clear()
            addAll(tvShow)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int
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
                    Glide.with(itemView.context)
                        .load(itemView.context.getString(R.string.url_poster, tvShow.posterPath))
                        .apply {
                            RequestOptions()
                                .placeholder(R.drawable.loading_animation)
                                .error(R.drawable.loading_animation)
                        }
                        .into(roundedPoster)
                }

                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailActivity::class.java).apply {
                        putExtra(EXTRA_TYPE, TypeDetail.TV_SHOW.ordinal)
                        putExtra(ID_DATA, tvShow.id)
                    }
                    itemView.context.startActivity(intent)
                }
            }
        }
}