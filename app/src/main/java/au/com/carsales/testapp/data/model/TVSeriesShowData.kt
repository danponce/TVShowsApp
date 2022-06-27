package au.com.carsales.testapp.data.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Dan on 24, junio, 2022
 * Copyright (c) 2022 Carsales. All rights reserved.
 */
data class TVSeriesShowData(

    @SerializedName("id")
    val id : Int?= null,

    @SerializedName("url")
    val url : String?= null,

    @SerializedName("name")
    val name : String?= null,

    @SerializedName("image")
    val image : ShowImageData?= null,

    @SerializedName("genres")
    val genres: List<String> ?= null,

    @SerializedName("rating")
    val rating : ShowRatingData?= null,

    @SerializedName("summary")
    val summary : String?= null,

    @SerializedName("schedule")
    val schedule: ShowScheduleData?= null

)