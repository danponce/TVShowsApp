package au.com.carsales.testapp.domain

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import au.com.carsales.testapp.data.model.TVSeriesShowData
import au.com.carsales.testapp.data.network.repository.ShowsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Created by Dan on 24, junio, 2022
 * Copyright (c) 2022 Carsales. All rights reserved.
 */
class GetShowsUseCase @Inject constructor(private val showsRepository: ShowsRepository) {

    fun getTVShows() : Flow<PagingData<TVSeriesShowData>> {
        return Pager(
            config = PagingConfig(200),
            pagingSourceFactory = { TVShowPageDataSource(showsRepository) }).flow
    }
}