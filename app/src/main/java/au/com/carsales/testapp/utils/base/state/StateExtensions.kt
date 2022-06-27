package au.com.carsales.testapp.utils.base.state

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import au.com.carsales.testapp.utils.base.State

/**
 * Created by Dan on 25, junio, 2022
 * Copyright (c) 2022 Carsales. All rights reserved.
 */
fun <T> LiveData<State<T>>.observeStateLiveData(
    lifecycleOwner: LifecycleOwner,
    onSuccess: ((State.Success<T>) -> Unit)? = null,
    onLoading: ((State.Loading<T>) -> Unit)? = null,
    onError: ((State.Error<*>) -> Unit)? = null
) {
    this.removeObservers(lifecycleOwner)
    this.observe(lifecycleOwner) {
        when (it) {
            is State.Success -> onSuccess?.invoke(it)
            is State.Loading -> onLoading?.invoke(it)
            is State.Error -> onError?.invoke(it)
            else -> {}
        }
    }
}