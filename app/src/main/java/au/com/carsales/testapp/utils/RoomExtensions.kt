package au.com.carsales.testapp.utils

import androidx.fragment.app.Fragment
import au.com.carsales.testapp.MyApplication

/**
 * Created by Dan on 27, junio, 2022
 * Copyright (c) 2022 Carsales. All rights reserved.
 */

fun Fragment.getTVShowsDatabase() = MyApplication.db

fun Fragment.getTVShowsDao() = MyApplication.db?.favoriteTVShowsDao()