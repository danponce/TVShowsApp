package au.com.carsales.testapp.ui.favorites

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import au.com.carsales.testapp.ui.model.TVSeriesShowViewData
import au.com.carsales.testapp.utils.base.State
import au.com.carsales.testapp.utils.base.coroutines.processAsyncJob
import au.com.carsales.testapp.utils.base.viewmodel.BaseBindingViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Dan on 27, junio, 2022
 * Copyright (c) 2022 Carsales. All rights reserved.
 */
class FavoritesViewModel @Inject constructor() : BaseBindingViewModel() {

    private val _favoritesLiveData = MutableLiveData<State<List<TVSeriesShowViewData>?>>()
    val favoritesLiveData : LiveData<State<List<TVSeriesShowViewData>?>> = _favoritesLiveData

    private suspend fun getFavoritesDBRequest() = FavoriteTVShowsManager.getAllFavorites()

    fun getFavoritesTVShows() {

        viewModelScope.launch(Dispatchers.IO) {
            processAsyncJob(
                block = {getFavoritesDBRequest()},
                result = {
                    val result = it?.filterNotNull()

                    _favoritesLiveData.postValue(State.success(result))
                },
                onError = {
                    _favoritesLiveData.postValue(State.error())
                }
            )
        }
    }

    fun deleteFromFavorites(item: TVSeriesShowViewData) {
        viewModelScope.launch(Dispatchers.IO) {
            FavoriteTVShowsManager.deleteItem(item)

            getFavoritesTVShows()
        }
    }
}