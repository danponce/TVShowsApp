package au.com.carsales.testapp.data.network

import android.content.Context
import android.os.Build
import okhttp3.Interceptor
import okhttp3.Response

/**
 * Created by Dan on 17, junio, 2022
 * Copyright (c) 2022 Carsales. All rights reserved.
 */
class HttpInterceptor() : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {

        val request = chain.request().newBuilder()
            .addHeader("CONNECT_TIMEOUT", "6000")
            .addHeader("READ_TIMEOUT", "6000")
            .addHeader("WRITE_TIMEOUT", "6000")
            .build()
        return chain.proceed(request)
    }

}