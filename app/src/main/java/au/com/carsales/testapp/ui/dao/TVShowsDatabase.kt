package au.com.carsales.testapp.ui.dao

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import au.com.carsales.testapp.ui.model.TVSeriesShowViewData


/**
 * Created by Dan on 27, junio, 2022
 * Copyright (c) 2022 Carsales. All rights reserved.
 */
@Database(entities = [TVSeriesShowViewData::class], version = 1, exportSchema = false)
@TypeConverters(TVShowTypeConverters::class)
abstract class TVShowsDatabase : RoomDatabase() {
    abstract fun favoriteTVShowsDao(): TVShowsDao?
}