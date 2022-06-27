package au.com.carsales.testapp.domain

import androidx.paging.PagingSource
import androidx.paging.PagingState
import au.com.carsales.testapp.data.model.TVSeriesData
import au.com.carsales.testapp.data.network.repository.ShowsRepository
import javax.inject.Inject

/**
 * Created by Dan on 27, junio, 2022
 * Copyright (c) 2022 Carsales. All rights reserved.
 */
private const val STARTING_PAGE_INDEX = 1

/**
 * Created by Dan on 27, junio, 2022
 * Copyright (c) 2022 Carsales. All rights reserved.
 */
class TVSeriesPageDataSource @Inject constructor(private val repository: ShowsRepository, private val searchQuery : String) : PagingSource<Int, TVSeriesData>() {

    override fun getRefreshKey(state: PagingState<Int, TVSeriesData>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, TVSeriesData> {
        val position = params.key ?: STARTING_PAGE_INDEX

        //Offset init in 0
        val offset = position - 1

        return try {
            val response = repository.getTVSeries(
                query = searchQuery
            )?.filterNotNull()

            val nextKey = if (response.isNullOrEmpty()) {
                null
            } else {
                position + 1
            }

            LoadResult.Page(
                data = response ?: listOf(),
                prevKey = if (position == STARTING_PAGE_INDEX) null else position - 1,
                nextKey = nextKey
            )
        } catch (exception: Exception) {
            LoadResult.Error(exception)
        }
    }
}