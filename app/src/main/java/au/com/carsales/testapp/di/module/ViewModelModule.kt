package au.com.carsales.testapp.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import au.com.carsales.testapp.ui.detail.TVShowDetailViewModel
import au.com.carsales.testapp.ui.home.HomeViewModel
import au.com.carsales.testapp.ui.detail.episode.EpisodeDetailViewModel
import au.com.carsales.testapp.ui.favorites.FavoritesViewModel
import au.com.carsales.testapp.utils.ViewModelFactory
import au.com.carsales.testapp.utils.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * Created by Dan on 17, junio, 2022
 * Copyright (c) 2022 Carsales. All rights reserved.
 */
@Module
abstract class ViewModelModule {

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    abstract fun homeViewModel(viewModel: HomeViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(EpisodeDetailViewModel::class)
    abstract fun episodeDetailViewModel(viewModel: EpisodeDetailViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(TVShowDetailViewModel::class)
    abstract fun tVShowDetailViewModel(viewModel: TVShowDetailViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(FavoritesViewModel::class)
    abstract fun favoritesViewModel(viewModel: FavoritesViewModel): ViewModel

}