package com.hariagus.submission2bajp.ui.tvshow

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.hariagus.submission2bajp.databinding.FragmentTvShowBinding
import com.hariagus.submission2bajp.utils.viewGone
import com.hariagus.submission2bajp.utils.viewVisible
import org.koin.androidx.viewmodel.ext.android.viewModel

class TvShowFragment : Fragment() {

    private lateinit var binding: FragmentTvShowBinding
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

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            val tvShowAdapter = TvShowAdapter()

            binding.progressSpinKitList.viewVisible()
            viewModel.getTvShow().observe(requireActivity(), { tvShow ->
                binding.progressSpinKitList.viewGone()
                tvShowAdapter.apply {
                    setTvShow(tvShow)
                    notifyDataSetChanged()
                }
            })

            with(binding.rvTvShow) {
                setHasFixedSize(true)
                adapter = tvShowAdapter
            }
        }
    }

}