package au.com.carsales.testapp.data.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Dan on 27, junio, 2022
 * Copyright (c) 2022 Carsales. All rights reserved.
 */
data class ShowScheduleData(

    @SerializedName("time")
    val time: String?= null,

    @SerializedName("days")
    val days: List<String>?= null
)
