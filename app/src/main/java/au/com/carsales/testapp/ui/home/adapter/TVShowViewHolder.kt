package au.com.carsales.testapp.ui.home.adapter

import androidx.recyclerview.widget.RecyclerView
import au.com.carsales.testapp.databinding.ViewCellTvshowBinding
import au.com.carsales.testapp.ui.model.TVSeriesShowViewData
import au.com.carsales.testapp.utils.base.databinding.BaseBindClickHandler

/**
 * Created by Dan on 27, junio, 2022
 * Copyright (c) 2022 Carsales. All rights reserved.
 */
class TVShowViewHolder (private val binding : ViewCellTvshowBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(data : TVSeriesShowViewData, click: BaseBindClickHandler<TVSeriesShowViewData>?) {
        binding.apply {
            item = data
            clickHandler = click
        }

        binding.executePendingBindings()
    }
}