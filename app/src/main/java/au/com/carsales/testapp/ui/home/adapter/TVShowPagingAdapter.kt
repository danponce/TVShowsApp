package au.com.carsales.testapp.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import au.com.carsales.testapp.databinding.ViewCellTvshowBinding
import au.com.carsales.testapp.ui.model.TVSeriesShowViewData
import au.com.carsales.testapp.utils.base.databinding.BaseBindClickHandler

class TVShowPagingAdapter : PagingDataAdapter<TVSeriesShowViewData, TVShowViewHolder>(TVShowSettDiffCallback())  {

    var clickHandler: BaseBindClickHandler<TVSeriesShowViewData>? = null

    override fun onBindViewHolder(holder: TVShowViewHolder, position: Int) {
        val viewData = getItem(position)
        viewData?.let {
            holder.apply {
                bind(viewData, clickHandler)
                itemView.tag = viewData
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TVShowViewHolder {
        return TVShowViewHolder(
            ViewCellTvshowBinding.inflate(
                LayoutInflater.from(parent.context), parent, false))
    }
}

private class TVShowSettDiffCallback : DiffUtil.ItemCallback<TVSeriesShowViewData>() {

    override fun areItemsTheSame(oldItem: TVSeriesShowViewData, newItem: TVSeriesShowViewData): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: TVSeriesShowViewData, newItem: TVSeriesShowViewData): Boolean {
        return oldItem == newItem
    }
}