package au.com.carsales.testapp.ui.mapper

import au.com.carsales.testapp.data.model.EpisodeData
import au.com.carsales.testapp.data.model.ShowImageData
import au.com.carsales.testapp.ui.model.EpisodeViewData
import au.com.carsales.testapp.ui.model.ShowImageViewData
import au.com.carsales.testapp.utils.base.Mapper
import javax.inject.Inject

/**
 * Created by Dan on 25, junio, 2022
 * Copyright (c) 2022 Carsales. All rights reserved.
 */
class EpisodeMapper @Inject constructor() : Mapper<EpisodeData?, EpisodeViewData?> {

    override fun executeMapping(type: EpisodeData?): EpisodeViewData? {
        return type?.let {
            EpisodeViewData(
                it.name,
                it.season,
                it.number,
                it.summary,
                mapImageData(it.image)
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
}