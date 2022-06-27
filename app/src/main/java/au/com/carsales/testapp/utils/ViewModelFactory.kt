package au.com.carsales.testapp.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.MapKey
import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton
import kotlin.reflect.KClass

/**
 * Created by Dan on 17, junio, 2022
 * Copyright (c) 2022 Carsales. All rights reserved.
 */

@Singleton
class ViewModelFactory @Inject constructor(private val viewModels: MutableMap<Class<out ViewModel>,
        @JvmSuppressWildcards Provider<ViewModel>>): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        viewModels[modelClass]?.get() as T
}

@Target(AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY_GETTER, AnnotationTarget.PROPERTY_SETTER)
@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
@MapKey
annotation class ViewModelKey(val value: KClass<out ViewModel>)

inline fun <reified VM : ViewModel> androidx.fragment.app.FragmentActivity.getViewModel(factory: ViewModelFactory): VM =
    ViewModelProvider(this, factory)[VM::class.java]


inline fun <reified VM : ViewModel> androidx.fragment.app.Fragment.getViewModel(factory: ViewModelFactory): VM =
    ViewModelProvider(this, factory)[VM::class.java]