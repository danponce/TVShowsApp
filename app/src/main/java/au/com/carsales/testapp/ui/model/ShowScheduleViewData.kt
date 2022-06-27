package au.com.carsales.testapp.ui.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * Created by Dan on 27, junio, 2022
 * Copyright (c) 2022 Carsales. All rights reserved.
 */
@Parcelize
class ShowScheduleViewData (
    val time: String?= null,
    val days: List<String>?= null
) : Parcelable {
    fun getDaysAndTimeList() : List<String> {
        val list = arrayListOf<String>()

        days?.forEach { day ->
            list.add("$day $time")
        }

        return list
    }
}