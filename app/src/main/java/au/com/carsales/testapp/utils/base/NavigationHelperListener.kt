package au.com.carsales.testapp.utils.base

import androidx.navigation.NavDirections

/**
 * Created by Dan on 16, junio, 2022
 * Copyright (c) 2022 Carsales. All rights reserved.
 */
interface NavigationHelperListener {

    fun navigate(directions : NavDirections)

    fun navigateBack() : Boolean
}