package au.com.carsales.testapp.ui.home

import android.os.Bundle
import android.view.*
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.paging.PagingData
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import au.com.carsales.testapp.R
import au.com.carsales.testapp.databinding.FragmentHomeBinding
import au.com.carsales.testapp.getAppComponentInjector
import au.com.carsales.testapp.ui.home.adapter.TVShowPagingAdapter
import au.com.carsales.testapp.ui.home.adapter.TVShowPagingFooterAdapter
import au.com.carsales.testapp.ui.model.TVSeriesShowViewData
import au.com.carsales.testapp.utils.DelayedSimpleQueryTextListener
import au.com.carsales.testapp.utils.ViewModelFactory
import au.com.carsales.testapp.utils.base.BaseDataBindingFragment
import au.com.carsales.testapp.utils.base.databinding.BaseBindClickHandler
import au.com.carsales.testapp.utils.base.databinding.SingleLayoutBindRecyclerAdapter
import au.com.carsales.testapp.utils.base.state.observeStateLiveData
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Dan on 16, junio, 2022
 * Copyright (c) 2022 Carsales. All rights reserved.
 *
 * Shows a list of TV shows
 */
class HomeFragment : BaseDataBindingFragment<FragmentHomeBinding>() {

    override fun layoutId(): Int = R.layout.fragment_home

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var homeViewModel : HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)

        getAppComponentInjector().inject(this)

        homeViewModel = ViewModelProvider(this, viewModelFactory)[HomeViewModel::class.java]

        return binding.root
    }

    private fun setObservers() {
        homeViewModel.tvShowsLiveData.observe(viewLifecycleOwner) {
            if(homeViewModel.lastSearchExecuted == HomeSearchType.PAGING_TYPE) {
                setPagingDataToRecyclerView(it)
            }
        }

        homeViewModel.tvShowSearchLiveData.observeStateLiveData(
            viewLifecycleOwner,
            onSuccess = {
                if(homeViewModel.lastSearchExecuted == HomeSearchType.COMMON_TYPE) {
                    setDataToRecyclerView(it.data)
                }
            })

        homeViewModel.tvShowsStateLiveData.observeStateLiveData(
            viewLifecycleOwner,
            onSuccess = {
                val adapter = (binding.tvShowsRecyclerView.adapter as? TVShowPagingAdapter)

                if(adapter?.itemCount == 0) {
                    homeViewModel.setEmptyStatus()
                }
            }
        )
    }

    private fun setPagingDataToRecyclerView(data : PagingData<TVSeriesShowViewData>) {
        viewLifecycleOwner.lifecycleScope.launch {
            (binding.tvShowsRecyclerView.adapter as TVShowPagingAdapter).submitData(data)
        }
    }

    private fun setDataToRecyclerView(data : List<TVSeriesShowViewData>) {
        val adapter = binding.tvShowsRecyclerView.adapter

        when(adapter) {
            is TVShowPagingAdapter-> setCommonRecyclerView(data)
            null -> setCommonRecyclerView(data)
            else -> (adapter as SingleLayoutBindRecyclerAdapter<TVSeriesShowViewData>).setData(data)
        }
    }

    private fun setCommonRecyclerView(data : List<TVSeriesShowViewData>?) {

        binding.tvShowsRecyclerView.apply {
            layoutManager = GridLayoutManager(requireContext(), 2)

            adapter = SingleLayoutBindRecyclerAdapter (
                R.layout.view_cell_tvshow,
                data,
                clickHandler = { _, item ->
                    goToDetailsScreen(item)
                })

            // Allows recycler view state restoration
            adapter?.stateRestorationPolicy = RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY
        }
    }

    private fun setPagingRecyclerView() {

        binding.tvShowsRecyclerView.apply {
            layoutManager = GridLayoutManager(requireContext(), 2)

            val pagingAdapter = TVShowPagingAdapter().apply {
                withLoadStateFooter(footer = TVShowPagingFooterAdapter())
                clickHandler = object : BaseBindClickHandler<TVSeriesShowViewData> {
                    override fun onClickView(view: View, item: TVSeriesShowViewData) {
                        goToDetailsScreen(item)
                    }
                }

                addLoadStateListener { loadState -> homeViewModel.manageStatePagingData(loadState) }

            }

            adapter = pagingAdapter

            // Allows recycler view state restoration
            adapter?.stateRestorationPolicy = RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY
        }
    }

    private fun goToDetailsScreen(data: TVSeriesShowViewData) {
        val direction = HomeFragmentDirections.goToDetailsAction(data)
        navigate(direction)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewModel = homeViewModel

        initView()

        val lastExecutedSearch = homeViewModel.lastSearchExecuted

        // Only make the request call when
        // there's no data on view model
        if(lastExecutedSearch == null) {
            homeViewModel.getTVShows()
        } else {
//            setLastDataToRecyclerView(lastExecutedSearch)
        }

        setObservers()

    }

    private fun setLastDataToRecyclerView(lastExecutedSearchType: HomeSearchType) {

        when(lastExecutedSearchType) {
            HomeSearchType.COMMON_TYPE -> setCommonRecyclerView(homeViewModel.getLastData())
            HomeSearchType.PAGING_TYPE -> {
                homeViewModel.getExistentTVShowData()?.let {
                    setPagingDataToRecyclerView(it)
                }
            }
        }

    }

    private fun initView() {

        when(homeViewModel.lastSearchExecuted) {
            null-> setPagingRecyclerView()
            HomeSearchType.PAGING_TYPE -> setPagingRecyclerView()
            HomeSearchType.COMMON_TYPE -> {
                setCommonRecyclerView(homeViewModel.getLastData())
            }
        }

        binding.searchView.apply {
            setOnQueryTextListener(
                DelayedSimpleQueryTextListener({ query ->
                    if(query.isNotEmpty() && query != homeViewModel.lastQuery) {
                        executeSearch(query)
                    }
                }))

            setOnCloseListener {
                setPagingRecyclerView()
                homeViewModel.getTVShows()
                false
            }
        }

    }

    private fun executeSearch(query: String) {
        homeViewModel.searchTVShows(query)
    }

}