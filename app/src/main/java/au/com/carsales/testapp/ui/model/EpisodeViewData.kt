package au.com.carsales.testapp.ui.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * Created by Dan on 25, junio, 2022
 * Copyright (c) 2022 Carsales. All rights reserved.
 */
@Parcelize
class EpisodeViewData(
    val name : String?= null,
    val season : Int?= null,
    val number : Int?= null,
    val summary : String?= null,
    val image : ShowImageViewData?= null
) : Parcelable {

    fun getEpisodeTitle() = (number ?: 0).toString() + " - " + name
}
