package au.com.carsales.testapp.ui.mapper

import au.com.carsales.testapp.data.model.ShowImageData
import au.com.carsales.testapp.data.model.ShowRatingData
import au.com.carsales.testapp.data.model.ShowScheduleData
import au.com.carsales.testapp.data.model.TVSeriesShowData
import au.com.carsales.testapp.ui.model.ShowImageViewData
import au.com.carsales.testapp.ui.model.ShowRatingViewData
import au.com.carsales.testapp.ui.model.ShowScheduleViewData
import au.com.carsales.testapp.ui.model.TVSeriesShowViewData
import au.com.carsales.testapp.utils.base.Mapper
import javax.inject.Inject

/**
 * Created by Dan on 25, junio, 2022
 * Copyright (c) 2022 Carsales. All rights reserved.
 */
class TVSeriesShowMapper @Inject constructor() : Mapper<TVSeriesShowData?, TVSeriesShowViewData?> {

    override fun executeMapping(type: TVSeriesShowData?): TVSeriesShowViewData? {
        return type?.let {
            TVSeriesShowViewData(
                it.id,
                it.url,
                it.name,
                mapImageData(it.image),
                it.genres,
                mapRatingData(it.rating),
                it.summary,
                mapSchedule(it.schedule)
            )
        }
    }

    private fun mapImageData(data : ShowImageData?) : ShowImageViewData? {
        return data?.let {
            ShowImageViewData(
                it.medium,
                it.original
            )
        }
    }

    private fun mapRatingData(data : ShowRatingData?) : ShowRatingViewData? {
        return data?.let {
            ShowRatingViewData(
                it.average
            )
        }
    }

    private fun mapSchedule(data: ShowScheduleData?) : ShowScheduleViewData? {
        return data?.let {
            ShowScheduleViewData(
                it.time,
                it.days
            )
        }
    }
}