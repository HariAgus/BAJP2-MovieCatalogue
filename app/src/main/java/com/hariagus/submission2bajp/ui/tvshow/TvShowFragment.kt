package com.hariagus.submission2bajp.ui.tvshow

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.hariagus.submission2bajp.data.source.local.entity.TvShowEntity
import com.hariagus.submission2bajp.databinding.FragmentTvShowBinding
import com.hariagus.submission2bajp.utils.viewGone
import com.hariagus.submission2bajp.utils.viewVisible
import org.koin.androidx.viewmodel.ext.android.viewModel

class TvShowFragment : Fragment() {

    private lateinit var binding: FragmentTvShowBinding

    private val tvShowAdapter by lazy { TvShowAdapter() }
    private val viewModel: TvShowViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTvShowBinding.inflate(
            layoutInflater, container, false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvTvShow.apply {
            setHasFixedSize(true)
            adapter = tvShowAdapter
        }

        if (activity != null) {
            binding.progressSpinKitList.viewVisible()
            viewModel.getTvShow().observe(requireActivity(), { tvShow ->
                binding.progressSpinKitList.viewGone()
                if (tvShow != null) {
                    showDataTvShow(tvShow)
                } else {
                    hideDataTvShow()
                }
            })
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun showDataTvShow(tvShow: List<TvShowEntity>) {
        tvShowAdapter.apply {
            setTvShow(tvShow)
            notifyDataSetChanged()
        }
    }

    private fun hideDataTvShow() {
        binding.apply {
            lottieEmptyTvshow.viewVisible()
            rvTvShow.viewGone()
        }
    }

}