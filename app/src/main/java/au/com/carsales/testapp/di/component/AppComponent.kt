package au.com.carsales.testapp.di.component

import au.com.carsales.testapp.di.module.ApiModule
import au.com.carsales.testapp.di.module.ViewModelModule
import au.com.carsales.testapp.ui.detail.TVShowDetailFragment
import au.com.carsales.testapp.ui.home.HomeFragment
import au.com.carsales.testapp.ui.detail.episode.EpisodeDetailFragment
import dagger.Component
import javax.inject.Singleton

/**
 * Created by Dan on 17, junio, 2022
 * Copyright (c) 2022 Carsales. All rights reserved.
 */

@Singleton
@Component(
    modules = [ApiModule::class, ViewModelModule::class])
interface AppComponent {
    fun inject(homeFragment: HomeFragment)
    fun inject(secondFragment: EpisodeDetailFragment)
    fun inject(tvShowDetailFragment: TVShowDetailFragment)
}