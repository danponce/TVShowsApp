package au.com.carsales.testapp.data.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Dan on 24, junio, 2022
 * Copyright (c) 2022 Carsales. All rights reserved.
 */
data class TVSeriesData(

    @SerializedName("score")
    val score : Double?= null,

    @SerializedName("show")
    val show: TVSeriesShowData?= null
)
