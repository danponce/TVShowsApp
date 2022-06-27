package au.com.carsales.testapp.ui.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import au.com.carsales.testapp.R

/**
 * Created by Dan on 27, junio, 2022
 * Copyright (c) 2022 Carsales. All rights reserved.
 */
class TVShowPagingFooterAdapter : LoadStateAdapter<TVShowPagingFooterAdapter.TVShowPagingLoadViewHolder>() {

    override fun onBindViewHolder(holder: TVShowPagingLoadViewHolder, loadState: LoadState) {
        val loaderContainer = holder.itemView.findViewById<ConstraintLayout>(R.id.loaderContainer)
        loaderContainer.isVisible = loadState !is LoadState.Loading
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): TVShowPagingLoadViewHolder {
        return TVShowPagingLoadViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.view_cell_paging_loader, parent, false)
        )
    }

    class TVShowPagingLoadViewHolder(view: View) : RecyclerView.ViewHolder(view)
}