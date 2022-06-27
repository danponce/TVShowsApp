package au.com.carsales.testapp.utils.base

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

/**
 * Created by Dan on 25, junio, 2022
 * Copyright (c) 2022 Carsales. All rights reserved.
 */
fun Toolbar.setBackButton(context: Activity, clickAction : () -> Unit) {
    if (context is AppCompatActivity) {
        context.setSupportActionBar(this)
        context.supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    this.setNavigationOnClickListener { clickAction.invoke() }
}