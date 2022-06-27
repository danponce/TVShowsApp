package au.com.carsales.testapp.domain

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import au.com.carsales.testapp.data.model.TVSeriesData
import au.com.carsales.testapp.data.model.TVSeriesShowData
import au.com.carsales.testapp.data.network.repository.ShowsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Created by Dan on 17, junio, 2022
 * Copyright (c) 2022 Carsales. All rights reserved.
 */
class GetSeriesSearchUseCase @Inject constructor(
    private val showsRepository: ShowsRepository) {

    suspend fun getTVSeriesSearch(query: String) = showsRepository.getTVSeries(query)
}