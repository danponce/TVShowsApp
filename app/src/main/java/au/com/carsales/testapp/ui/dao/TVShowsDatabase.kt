package au.com.carsales.testapp.ui.dao

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import au.com.carsales.testapp.ui.model.TVSeriesShowViewData


/**
 * Created by Dan on 27, junio, 2022
 * Copyright (c) 2022 Carsales. All rights reserved.
 */
@Database(entities = [TVSeriesShowViewData::class], version = 1)
abstract class TVShowsDatabase : RoomDatabase() {
    abstract fun favoriteTVShowsDao(): TVShowsDao?
}