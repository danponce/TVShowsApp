package au.com.carsales.testapp.ui.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


/**
 * Created by Dan on 25, junio, 2022
 * Copyright (c) 2022 Carsales. All rights reserved.
 */
@Parcelize
class ShowRatingViewData (
    val average : Double ?= null
) : Parcelable {

    fun getFormattedAverage() : String {
        return when {
            average == null -> ""
            else -> average.toString()
        }
    }
}