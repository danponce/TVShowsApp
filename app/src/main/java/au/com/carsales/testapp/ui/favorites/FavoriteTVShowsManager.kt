package au.com.carsales.testapp.ui.favorites

import au.com.carsales.testapp.MyApplication
import au.com.carsales.testapp.ui.model.TVSeriesShowViewData

/**
 * Created by Dan on 27, junio, 2022
 * Copyright (c) 2022 Carsales. All rights reserved.
 */
object FavoriteTVShowsManager {

    private fun getFavoriteDao() = MyApplication.db?.favoriteTVShowsDao()!!

    fun insertItem(item : TVSeriesShowViewData) {
        getFavoriteDao().addData(item)
    }

    fun deleteItem(item : TVSeriesShowViewData) {
        getFavoriteDao().delete(item)
    }

    suspend fun getAllFavorites() = getFavoriteDao().getFavoriteTVShows()

    fun isFavorite(id: Int) : Boolean = getFavoriteDao().isFavorite(id) == 1

}