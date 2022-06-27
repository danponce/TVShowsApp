package au.com.carsales.testapp.utils.base.coroutines

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async

/**
 * Created by Dan on 25, junio, 2022
 * Copyright (c) 2022 Carsales. All rights reserved.
 */

fun <T> CoroutineScope.processAsyncJob(block: suspend () -> T, result: (T) -> Unit, onError: (Exception) -> Unit) {
    async {
        try {
            result(block())
        } catch (e: Exception) {
            e.printStackTrace()
            onError(e)
        }
    }
}