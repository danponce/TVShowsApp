package au.com.carsales.testapp.data.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Dan on 25, junio, 2022
 * Copyright (c) 2022 Carsales. All rights reserved.
 */
data class EpisodeData(
    @SerializedName("name")
    val name : String?= null,

    @SerializedName("season")
    val season : Int?= null,

    @SerializedName("number")
    val number : Int?= null,

    @SerializedName("summary")
    val summary : String?= null,

    @SerializedName("image")
    val image : ShowImageData?= null
)
