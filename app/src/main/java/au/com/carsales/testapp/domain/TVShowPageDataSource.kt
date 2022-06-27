package au.com.carsales.testapp.domain

import androidx.paging.PagingSource
import androidx.paging.PagingState
import au.com.carsales.testapp.data.model.TVSeriesShowData
import au.com.carsales.testapp.data.network.repository.ShowsRepository
import javax.inject.Inject

private const val STARTING_PAGE_INDEX = 1

/**
 * Created by Dan on 27, junio, 2022
 * Copyright (c) 2022 Carsales. All rights reserved.
 */
class TVShowPageDataSource @Inject constructor(private val repository: ShowsRepository) : PagingSource<Int, TVSeriesShowData>() {

    override fun getRefreshKey(state: PagingState<Int, TVSeriesShowData>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, TVSeriesShowData> {
        val position = params.key ?: STARTING_PAGE_INDEX

        //Offset init in 0
        val offset = position - 1

        return try {
            val response = repository.getTVShows(
                page = offset)?.filterNotNull()

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
        }  catch (exception: Exception) {
            LoadResult.Error(exception)
        }
    }
}