package com.hariagus.submission2bajp.ui.tvshow

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hariagus.submission2bajp.R
import com.hariagus.submission2bajp.data.source.local.entity.TvShowEntity
import com.hariagus.submission2bajp.databinding.ItemListBinding
import com.hariagus.submission2bajp.ui.detail.DetailActivity
import com.hariagus.submission2bajp.ui.detail.DetailActivity.Companion.EXTRA_TYPE
import com.hariagus.submission2bajp.ui.detail.DetailActivity.Companion.ID_DATA
import com.hariagus.submission2bajp.ui.detail.TypeCatalogue
import com.hariagus.submission2bajp.utils.loadImageGlideAnim
import com.hariagus.submission2bajp.utils.startActivity

class TvShowAdapter : RecyclerView.Adapter<TvShowAdapter.TvShowViewHolder>() {

    private val listTvShow = ArrayList<TvShowEntity>()

    fun setTvShow(tvShow: List<TvShowEntity>?) {
        if (tvShow == null) return
        listTvShow.apply {
            clear()
            addAll(tvShow)
        }
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

            itemView.setOnClickListener {
                itemView.context.startActivity<DetailActivity>(
                    EXTRA_TYPE to TypeCatalogue.TV_SHOW.ordinal,
                    ID_DATA to tvShow.id
                )
            }
        }

    }
}