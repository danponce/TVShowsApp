package au.com.carsales.testapp.data.network

/**
 * Created by Dan on 17, junio, 2022
 * Copyright (c) 2022 Carsales. All rights reserved.
 */
object RestConstants {

    const val QUERY_PREFIX = "q"
    const val PAGE_PREFIX = "page"

    const val SEARCH_SHOWS = "/search/shows"
    const val MAIN_SHOWS_INFO = "/shows"
    const val SHOWS_SEARCH = "/search/shows"

    const val GET_EPISODES = "/shows/{showId}/episodes"

    const val SHOW_ID_KEY = "showId"
}