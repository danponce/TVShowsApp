package au.com.carsales.testapp.ui.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * Created by Dan on 25, junio, 2022
 * Copyright (c) 2022 Carsales. All rights reserved.
 */
@Parcelize
@Entity(tableName="favoriteTVShow")
data class TVSeriesShowViewData (
    @PrimaryKey
    val id : Int?= null,
    val url : String?= null,
    val name : String?= null,
    val image : ShowImageViewData?= null,
    val genres: List<String> ?= null,
    val rating : ShowRatingViewData?= null,
    val summary : String ?= null,
    val schedule : ShowScheduleViewData?= null
) : Parcelable