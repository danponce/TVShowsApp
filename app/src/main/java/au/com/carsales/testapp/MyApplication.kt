package au.com.carsales.testapp

import android.app.Application
import androidx.fragment.app.Fragment
import au.com.carsales.testapp.di.component.AppComponent
import au.com.carsales.testapp.di.component.DaggerAppComponent

/**
 * Created by Dan on 17, junio, 2022
 * Copyright (c) 2022 Carsales. All rights reserved.
 */
class MyApplication : Application() {

    companion object {
        var appComponent : AppComponent?= null
    }

    override fun onCreate() {
        super.onCreate()

        getDagger()

    }
}

fun getDagger() : AppComponent {
    return when(MyApplication.appComponent) {
        null -> {
            MyApplication.appComponent = DaggerAppComponent
                .builder()
                .build()
            MyApplication.appComponent!!
        }

        else -> MyApplication.appComponent!!
    }
}

fun Fragment.getAppComponentInjector() = getDagger()