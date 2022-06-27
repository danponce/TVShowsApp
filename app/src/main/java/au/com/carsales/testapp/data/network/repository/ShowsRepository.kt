package au.com.carsales.testapp.data.network.repository

import au.com.carsales.testapp.data.model.EpisodeData
import au.com.carsales.testapp.data.model.TVSeriesData
import au.com.carsales.testapp.data.model.TVSeriesShowData
import au.com.carsales.testapp.data.network.RestAPI
import au.com.carsales.testapp.ui.model.EpisodeViewData
import au.com.carsales.testapp.utils.base.SafeAPIRepository
import retrofit2.Response
import javax.inject.Inject

/**
 * Created by Dan on 17, junio, 2022
 * Copyright (c) 2022 Carsales. All rights reserved.
 */
class ShowsRepository @Inject constructor(
    private val api: RestAPI
) : SafeAPIRepository() {

    suspend fun getTVSeries(query: String) : List<TVSeriesData?>? {
        return safeApiListCall {
            api.getTVShowsSearch(query)
        }
    }

    suspend fun getTVShows(page: Int) : List<TVSeriesShowData?>? {
        return safeApiListCall {
            api.getTVShows(page)
        }
    }

    suspend fun getEpisodeList(showId: Int) : List<EpisodeData?>? {
        return safeApiListCall {
            api.getEpisodes(showId)
        }
    }


}