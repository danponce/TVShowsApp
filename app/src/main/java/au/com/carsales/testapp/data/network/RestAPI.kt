package au.com.carsales.testapp.data.network

import au.com.carsales.testapp.data.model.EpisodeData
import au.com.carsales.testapp.data.model.TVSeriesData
import au.com.carsales.testapp.data.model.TVSeriesShowData
import au.com.carsales.testapp.data.network.RestConstants.GET_EPISODES
import au.com.carsales.testapp.data.network.RestConstants.MAIN_SHOWS_INFO
import au.com.carsales.testapp.data.network.RestConstants.PAGE_PREFIX
import au.com.carsales.testapp.data.network.RestConstants.QUERY_PREFIX
import au.com.carsales.testapp.data.network.RestConstants.SEARCH_SHOWS
import au.com.carsales.testapp.data.network.RestConstants.SHOWS_SEARCH
import au.com.carsales.testapp.data.network.RestConstants.SHOW_ID_KEY
import au.com.carsales.testapp.ui.model.EpisodeViewData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by Dan on 17, junio, 2022
 * Copyright (c) 2022 Carsales. All rights reserved.
 */
interface RestAPI {

    @GET(MAIN_SHOWS_INFO)
    suspend fun getTVShows(
        @Query(PAGE_PREFIX) page: Int
    ) : Response<List<TVSeriesShowData?>?>

    @GET(SHOWS_SEARCH)
    suspend fun getTVShowsSearch(
        @Query(QUERY_PREFIX) query: String
    ) : Response<List<TVSeriesData?>?>

    @GET(GET_EPISODES)
    suspend fun getEpisodes(
        @Path(SHOW_ID_KEY) showId : Int
    ) : Response<List<EpisodeData?>?>
}