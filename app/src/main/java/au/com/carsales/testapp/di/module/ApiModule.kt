package au.com.carsales.testapp.di.module

import au.com.carsales.testapp.BuildConfig
import au.com.carsales.testapp.data.network.HttpInterceptor
import au.com.carsales.testapp.data.network.RestAPI
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

/**
 * Created by Dan on 17, junio, 2022
 * Copyright (c) 2022 Carsales. All rights reserved.
 */
@Module
class ApiModule {

    companion object {
        private const val CONNECT_TIMEOUT = 120L
        private const val READ_TIMEOUT = 120L
        private const val WRITE_TIMEOUT = 120L
    }

    @Provides
    @Singleton
    @Named("provideHttpClient")
    fun provideHttpClient() : OkHttpClient {
        val httpLoggingInterceptor = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger.DEFAULT)
        val clientBuilder = OkHttpClient.Builder()

        clientBuilder.addInterceptor(HttpInterceptor())

        if (BuildConfig.DEBUG) {
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            clientBuilder.addInterceptor(httpLoggingInterceptor)
        }

        clientBuilder.connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
        clientBuilder.writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
        clientBuilder.readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
        return clientBuilder.build()
    }

    @Provides
    @Singleton
    @Named("provideRetrofit")
    fun provideRetrofit(@Named("provideHttpClient") okHttpClient: OkHttpClient) : Retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL_ENDPOINT)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    fun provideAPI(@Named("provideRetrofit") retrofit: Retrofit) = retrofit.create(RestAPI::class.java)
}