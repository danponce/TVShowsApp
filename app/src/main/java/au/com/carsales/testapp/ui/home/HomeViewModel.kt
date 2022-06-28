package au.com.carsales.testapp.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import au.com.carsales.testapp.domain.GetSeriesSearchUseCase
import au.com.carsales.testapp.domain.GetShowsUseCase
import au.com.carsales.testapp.ui.mapper.TVSeriesShowMapper
import au.com.carsales.testapp.ui.model.TVSeriesShowViewData
import au.com.carsales.testapp.utils.base.State
import au.com.carsales.testapp.utils.base.coroutines.processAsyncJob
import au.com.carsales.testapp.utils.base.viewmodel.BaseBindingViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * Created by Dan on 17, junio, 2022
 * Copyright (c) 2022 Carsales. All rights reserved.
 */
class HomeViewModel @Inject constructor(
    private val tvSeriesShowMapper: TVSeriesShowMapper,
    private val getShowsUseCase: GetShowsUseCase,
    private val getShowsSearchUseCase: GetSeriesSearchUseCase
) : BaseBindingViewModel() {

    private val _tvShowsLiveData = MutableLiveData<PagingData<TVSeriesShowViewData>>()
    val tvShowsLiveData: LiveData<PagingData<TVSeriesShowViewData>> = _tvShowsLiveData

    private val _tvShowsSearchLiveData = MutableLiveData<State<List<TVSeriesShowViewData>>>()
    val tvShowSearchLiveData: LiveData<State<List<TVSeriesShowViewData>>> = _tvShowsSearchLiveData

    val tvShowsStateLiveData = MutableLiveData<State<Unit>>()

    var lastSearchExecuted : HomeSearchType? = null

    var lastQuery : String?= null

    fun getExistentTVShowData(): PagingData<TVSeriesShowViewData>? {
        return when (tvShowsStateLiveData.value) {
            is State.Success -> _tvShowsLiveData.value
            else -> null
        }
    }

    suspend fun searchTVShowsRequest(query: String) = withContext(Dispatchers.IO) {
        getShowsSearchUseCase.getTVSeriesSearch(query)
    }

    fun searchTVShows(query: String) {

        setLoadingStatus()

        lastQuery = query
        lastSearchExecuted = HomeSearchType.COMMON_TYPE

        viewModelScope.launch {
            processAsyncJob(
                block = { searchTVShowsRequest(query) },
                result = {
                    if (it == null) {
                        _tvShowsSearchLiveData.postValue(State.error())
                        setErrorStatus()
                    } else {

                        if(it.isEmpty()) {
                            _tvShowsSearchLiveData.postValue(State.empty())
                            setEmptyStatus()
                        } else {
                            val mappedResult = it.filterNotNull()
                                .mapNotNull { item -> tvSeriesShowMapper.executeMapping(item.show) }

                            _tvShowsSearchLiveData.postValue(State.success(mappedResult))

                            setSuccessStatus()
                        }
                    }
                },
                onError = {
                    _tvShowsSearchLiveData.postValue(State.error())
                    setErrorStatus()
                }
            )
        }

    }

    fun getTVShows() {
        viewModelScope.launch {

            lastSearchExecuted = HomeSearchType.PAGING_TYPE

            getShowsUseCase.getTVShows()
                .map { pagingData ->
                    pagingData.map {
                        tvSeriesShowMapper.executeMapping(it) ?: TVSeriesShowViewData()
                    }
                }
                .cachedIn(this).collectLatest {
                    _tvShowsLiveData.value = it
                }

        }
    }

    fun manageStatePagingData(loadState: CombinedLoadStates) {
        when (loadState.refresh) {
            //Handle Errors
            is LoadState.Error -> {
                setErrorStatus()
                tvShowsStateLiveData.postValue(State.error())
            }
            //Handle Loading
            is LoadState.Loading -> {
                setLoadingStatus()
                tvShowsStateLiveData.postValue(State.loading())
            }
            //Handle no load active operation and no error
            is LoadState.NotLoading -> {
                setSuccessStatus()
                tvShowsStateLiveData.postValue(State.success(Unit))
            }
        }
    }

    fun getLastData() : List<TVSeriesShowViewData>? {
        return when(tvShowSearchLiveData.value) {
            is State.Success -> (tvShowSearchLiveData.value as State.Success).data
            else -> null
        }
    }
}