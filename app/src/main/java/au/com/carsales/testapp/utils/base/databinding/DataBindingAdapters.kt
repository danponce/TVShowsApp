package au.com.carsales.testapp.utils.base.databinding

import android.os.Build
import android.text.Html
import android.widget.ImageView
import android.widget.TextView
import androidx.core.text.HtmlCompat
import androidx.databinding.BindingAdapter
import au.com.carsales.testapp.R
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

/**
 * Created by Dan on 25, junio, 2022
 * Copyright (c) 2022 Carsales. All rights reserved.
 */
@BindingAdapter("loadImageUrl")
fun loadImageUrl(imageView: ImageView, url: String?) {
    Glide.with(imageView.context)
        .load(url)
        .placeholder(R.drawable.ic_default_image_placeholder_48)
        .error(R.drawable.ic_baseline_broken_image_48)
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .into(imageView)
}

@BindingAdapter("setHTMLText")
fun setHTMLText(textView: TextView, text: String?) {
    if(text == null) {
        return
    }

    textView.text = HtmlCompat.fromHtml(text, HtmlCompat.FROM_HTML_MODE_LEGACY)
}