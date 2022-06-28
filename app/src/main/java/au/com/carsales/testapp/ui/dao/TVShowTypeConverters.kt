package au.com.carsales.testapp.ui.dao

import androidx.room.TypeConverter
import au.com.carsales.testapp.ui.model.ShowImageViewData
import au.com.carsales.testapp.ui.model.ShowRatingViewData
import au.com.carsales.testapp.ui.model.ShowScheduleViewData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type


/**
 * Created by Dan on 27, junio, 2022
 * Copyright (c) 2022 Carsales. All rights reserved.
 */
class TVShowTypeConverters {

    @TypeConverter
    fun showImageToString(image : ShowImageViewData) : String = Gson().toJson(image)

    @TypeConverter
    fun stringToImage(string : String) : ShowImageViewData = Gson().fromJson(string, ShowImageViewData::class.java)

    @TypeConverter
    fun showRatingToString(image : ShowRatingViewData) : String = Gson().toJson(image)

    @TypeConverter
    fun stringToRating(string : String) : ShowRatingViewData = Gson().fromJson(string, ShowRatingViewData::class.java)

    @TypeConverter
    fun showScheduleToString(image : ShowScheduleViewData) : String = Gson().toJson(image)

    @TypeConverter
    fun stringToSchedule(string : String) : ShowScheduleViewData = Gson().fromJson(string, ShowScheduleViewData::class.java)

    @TypeConverter
    fun fromStringToList(value: String?): List<String?>? {
        val listType: Type = object : TypeToken<List<String?>?>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromListToString(list: List<String?>?): String? {
        val gson = Gson()
        return gson.toJson(list)
    }
}