package com.hariagus.submission2bajp.ui.movie

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.hariagus.submission2bajp.databinding.FragmentMovieBinding
import com.hariagus.submission2bajp.utils.viewGone
import com.hariagus.submission2bajp.utils.viewVisible
import org.koin.androidx.viewmodel.ext.android.viewModel

class MovieFragment : Fragment() {

    private lateinit var binding: FragmentMovieBinding

    private val movieAdapter by lazy { MovieAdapter() }
    private val viewModel: MovieViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMovieBinding.inflate(
            layoutInflater, container, false
        )
        return binding.root
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvMovie.apply {
            setHasFixedSize(true)
            adapter = movieAdapter
        }

        if (activity != null) {
            binding.progressSpinKitList.viewVisible()
            viewModel.getMovies().observe(requireActivity(), { movies ->
                binding.progressSpinKitList.viewGone()
                movieAdapter.apply {
                    setMovies(movies)
                    notifyDataSetChanged()
                }
            })
        }
    }

}