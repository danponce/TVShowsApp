package au.com.carsales.testapp.ui.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import au.com.carsales.testapp.ui.model.TVSeriesShowViewData

/**
 * Created by Dan on 27, junio, 2022
 * Copyright (c) 2022 Carsales. All rights reserved.
 */
@Dao
interface TVShowsDao {

    @Insert
    fun addData(favoriteTVShow: TVSeriesShowViewData?)

    @Query("select * from favoriteTVShow")
    fun getFavoriteTVShows(): List<TVSeriesShowViewData?>?

    @Query("SELECT EXISTS (SELECT 1 FROM favoriteTVShow WHERE id=:id)")
    fun isFavorite(id: Int): Int

    @Delete
    fun delete(favoriteTVShow: TVSeriesShowViewData?)
}