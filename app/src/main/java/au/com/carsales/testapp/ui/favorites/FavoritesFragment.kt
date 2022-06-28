package au.com.carsales.testapp.ui.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import au.com.carsales.testapp.R
import au.com.carsales.testapp.databinding.FragmentFavoritesBinding
import au.com.carsales.testapp.getAppComponentInjector
import au.com.carsales.testapp.ui.model.TVSeriesShowViewData
import au.com.carsales.testapp.utils.ViewModelFactory
import au.com.carsales.testapp.utils.base.BaseViewBindingFragment
import au.com.carsales.testapp.utils.base.databinding.SingleLayoutBindRecyclerAdapter
import au.com.carsales.testapp.utils.base.setBackButton
import au.com.carsales.testapp.utils.base.state.observeStateLiveData
import au.com.carsales.testapp.utils.getViewModel
import javax.inject.Inject

/**
 * Created by Dan on 27, junio, 2022
 * Copyright (c) 2022 Carsales. All rights reserved.
 */
class FavoritesFragment : BaseViewBindingFragment<FragmentFavoritesBinding>() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var favoritesViewModel: FavoritesViewModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        
        getAppComponentInjector().inject(this)
        
        initViewModel()
        initObservers()

        binding.newToolbar.setBackButton(requireActivity()) { navigateBack() }

        return binding.root
    }
    
    private fun initViewModel() {
        favoritesViewModel = getViewModel(viewModelFactory)
    }
    
    private fun initObservers() {
        favoritesViewModel.favoritesLiveData.observeStateLiveData(
            viewLifecycleOwner,
            onSuccess = { setFavoritesRecyclerView(it.data) }
        )
    }
    
    private fun setFavoritesRecyclerView(favoritesList : List<TVSeriesShowViewData>?) {
        binding.favoritesRecyclerView.apply { 
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = SingleLayoutBindRecyclerAdapter(
                R.layout.view_cell_favorite,
                favoritesList,
                clickHandler = { view, item ->

                    when(view.id) {
                        R.id.favoriteContainer -> goToDetailScreen(item)

                        R.id.favoriteImageView -> {
                            favoritesViewModel.deleteFromFavorites(item)
                        }
                    }
                    
                }
            )
        }
    }

    private fun goToDetailScreen(item : TVSeriesShowViewData) {
        val direction = FavoritesFragmentDirections.goToEpisodeDetailAction(item)

        navigate(direction)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        favoritesViewModel.getFavoritesTVShows()
    }
}