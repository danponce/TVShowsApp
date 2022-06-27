package au.com.carsales.testapp.ui.detail.episode

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import au.com.carsales.testapp.R
import au.com.carsales.testapp.databinding.FragmentEpisodeListBinding
import au.com.carsales.testapp.getAppComponentInjector
import au.com.carsales.testapp.ui.detail.TVShowDetailViewModel
import au.com.carsales.testapp.utils.ViewModelFactory
import au.com.carsales.testapp.utils.base.BaseDataBindingFragment
import au.com.carsales.testapp.utils.base.databinding.SingleLayoutBindRecyclerAdapter
import au.com.carsales.testapp.utils.getViewModel
import javax.inject.Inject

/**
 * Created by Dan on 25, junio, 2022
 * Copyright (c) 2022 Carsales. All rights reserved.
 */
class EpisodeListFragment : BaseDataBindingFragment<FragmentEpisodeListBinding>() {

    override fun layoutId(): Int = R.layout.fragment_episode_list

    private val detailViewModel : TVShowDetailViewModel by viewModels({ requireParentFragment() })

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    companion object {
        const val ARG_EPISODE_SEASON = "episodeSeason"

        fun newInstance(episodesSeason: EpisodesSeason) =
            EpisodeListFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(ARG_EPISODE_SEASON, episodesSeason)
                }
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (arguments?.getParcelable(ARG_EPISODE_SEASON) as EpisodesSeason?)?.let { season ->
                setEpisodesRecyclerView(season)
        }

    }

    private fun setEpisodesRecyclerView(season : EpisodesSeason) {
        binding.episodesRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = SingleLayoutBindRecyclerAdapter(
                R.layout.view_cell_episode,
                season.episodes,
                clickHandler = { _, item ->
                    detailViewModel.setClickedEpisode(item)
                }
            )
        }
    }

}