package com.hariagus.submission2bajp.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.hariagus.submission2bajp.R
import com.hariagus.submission2bajp.data.source.local.entity.TrendingEntity
import com.hariagus.submission2bajp.databinding.ItemTrendingBinding
import com.hariagus.submission2bajp.utils.CatalogueDiffUtil
import com.hariagus.submission2bajp.utils.loadImageGlide
import com.smarteist.autoimageslider.SliderViewAdapter

class BannerAdapter : SliderViewAdapter<BannerAdapter.ViewHolder>() {

    private var listBanner = emptyList<TrendingEntity>()
    private val sizeBanner = 5

    fun setMovies(banner: List<TrendingEntity>) {
        val bannerDiffUtil = CatalogueDiffUtil(listBanner, banner)
        DiffUtil.calculateDiff(bannerDiffUtil)
        listBanner = banner
    }

    override fun onCreateViewHolder(parent: ViewGroup?): ViewHolder {
        val binding = ItemTrendingBinding.inflate(
            LayoutInflater.from(parent?.context),
            parent, false
        )

        return ViewHolder(binding)
    }

    override fun getCount(): Int = sizeBanner


    override fun onBindViewHolder(viewHolder: ViewHolder?, position: Int) {
        viewHolder?.bind(listBanner[position])
    }

    class ViewHolder(private val binding: ItemTrendingBinding) :
        SliderViewAdapter.ViewHolder(binding.root) {

        fun bind(trendingEntity: TrendingEntity) {
            binding.tvTitle.text = trendingEntity.title
            itemView.context.loadImageGlide(
                itemView.context.getString(
                    R.string.url_poster, trendingEntity.poster_path
                ),
                binding.roundedPoster
            )
        }

    }

}