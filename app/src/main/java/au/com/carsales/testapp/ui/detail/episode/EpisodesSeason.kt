package au.com.carsales.testapp.ui.detail.episode

import android.os.Parcelable
import au.com.carsales.testapp.ui.model.EpisodeViewData
import kotlinx.parcelize.Parcelize

/**
 * Created by Dan on 25, junio, 2022
 * Copyright (c) 2022 Carsales. All rights reserved.
 */
@Parcelize
data class EpisodesSeason (
    val season : Int,
    val episodes : List<EpisodeViewData>
    ) : Parcelable