package au.com.carsales.testapp.domain

import au.com.carsales.testapp.data.network.repository.ShowsRepository
import javax.inject.Inject

/**
 * Created by Dan on 25, junio, 2022
 * Copyright (c) 2022 Carsales. All rights reserved.
 */
class GetEpisodesUseCase @Inject constructor(private val showsRepository: ShowsRepository) {

    suspend fun getEpisodes(showId : Int) = showsRepository.getEpisodeList(showId)
}