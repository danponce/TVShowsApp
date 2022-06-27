package au.com.carsales.testapp.ui.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

/**
 * Created by Dan on 25, junio, 2022
 * Copyright (c) 2022 Carsales. All rights reserved.
 */
@Parcelize
data class ShowImageViewData (
    val medium : String?= null,
    val original: String?= null
) : Parcelable