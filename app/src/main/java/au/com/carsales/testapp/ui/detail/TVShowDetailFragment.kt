package au.com.carsales.testapp.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import au.com.carsales.testapp.R
import au.com.carsales.testapp.databinding.FragmentTvshowDetailBinding
import au.com.carsales.testapp.getAppComponentInjector
import au.com.carsales.testapp.ui.detail.episode.EpisodeListFragment
import au.com.carsales.testapp.ui.detail.episode.EpisodesPagerAdapter
import au.com.carsales.testapp.ui.detail.episode.EpisodesSeason
import au.com.carsales.testapp.ui.model.TVSeriesShowViewData
import au.com.carsales.testapp.utils.ViewModelFactory
import au.com.carsales.testapp.utils.base.BaseDataBindingFragment
import au.com.carsales.testapp.utils.base.LiveEvent
import au.com.carsales.testapp.utils.base.databinding.SingleLayoutBindRecyclerAdapter
import au.com.carsales.testapp.utils.base.setBackButton
import au.com.carsales.testapp.utils.base.state.observeStateLiveData
import com.google.android.material.chip.Chip
import com.google.android.material.tabs.TabLayoutMediator
import javax.inject.Inject

/**
 * Created by Dan on 25, junio, 2022
 * Copyright (c) 2022 Carsales. All rights reserved.
 */
class TVShowDetailFragment : BaseDataBindingFragment<FragmentTvshowDetailBinding>() {

    override fun layoutId(): Int = R.layout.fragment_tvshow_detail

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var detailViewModel: TVShowDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        super.onCreateView(inflater, container, savedInstanceState)

        getAppComponentInjector().inject(this)

        detailViewModel = ViewModelProvider(this, viewModelFactory)[TVShowDetailViewModel::class.java]

        setObservers()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModelData = detailViewModel.getExistentEpisodesData()

        if(viewModelData != null) {
            setEpisodesTabLayout(detailViewModel.getEpisodesSeasons())
            val lastShow = detailViewModel.getLastShow()

            // Set again views with last show from API
            lastShow?.let {
                setView(it)
            }

        } else {
            val args : TVShowDetailFragmentArgs by navArgs()

            val tvShow = args.tvShow

            detailViewModel.setTVShowData(tvShow)
            detailViewModel.getEpisodes()

            setView(tvShow)
        }

        detailViewModel.isShowFavorite()

        binding.viewModel = detailViewModel
        binding.lifecycleOwner = viewLifecycleOwner

        binding.toolbar.setBackButton(requireActivity()) { navigateBack() }

        initListeners()
    }

    private fun initListeners() {

        binding.fab.setOnClickListener {
            when(detailViewModel.isActualShowFavorite()) {
                true -> detailViewModel.deleteFavorite()
                false -> detailViewModel.addFavorite()
            }
        }

    }

    private fun setFabDrawable(isFavorite : Boolean) {
        val drawable = ContextCompat.getDrawable(
            requireContext(),
            if(isFavorite) {
                R.drawable.ic_baseline_favorite_24
            } else { R.drawable.ic_baseline_favorite_border_24 })

        binding.fab.setImageDrawable(drawable)
    }

    private fun setView(data: TVSeriesShowViewData) {
        binding.toolbar.title = data.name

        // Add the genre Chips
        data.genres?.forEach {
            val chip = Chip(requireContext())
            chip.text = it

            binding.chipGroupView.addView(chip)
        }

        binding.scheduleRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = SingleLayoutBindRecyclerAdapter(
                R.layout.view_cell_tvshow_schedule,
                data.schedule?.getDaysAndTimeList()
            )
        }
    }

    private fun setEpisodesTabLayout(episodesSeason: List<EpisodesSeason>) {
        binding.apply {
            val fragmentList = arrayListOf<EpisodeListFragment>()

            episodesSeason.forEach {
                fragmentList.add(EpisodeListFragment.newInstance(it))
            }

            val adapter =
                EpisodesPagerAdapter(
                    this@TVShowDetailFragment,
                    fragmentList)
            viewPager.offscreenPageLimit = 1
            viewPager.adapter = adapter

            TabLayoutMediator(tabLayout, viewPager) { tab, position ->

                tab.text = getString(R.string.episodes_season_tab_title, position + 1)

            }.attach()
        }
    }

    private fun setObservers() {
        detailViewModel.apply {

            episodesLiveData.observeStateLiveData(
                lifecycleOwner = viewLifecycleOwner,
                onSuccess = { setEpisodesTabLayout(detailViewModel.getEpisodesSeasons()) }
            )

            episodeClickedLiveData.let {
                LiveEvent(it).observe(viewLifecycleOwner) {
                    val direction = TVShowDetailFragmentDirections.goToEpisodeDetailAction(it)
                    navigate(direction)
                }
            }

            isFavoriteLiveData.observe(viewLifecycleOwner) {
                setFabDrawable(it)
            }

        }
    }
}