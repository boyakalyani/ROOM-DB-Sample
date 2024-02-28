package com.example.myapplication.apidata

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.example.myapplication.dataclasses.Course

class Converters {
    @TypeConverter
    fun fromCourseList(value: List<Course>): String {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun toCourseList(value: String): List<Course> {
        val listType = object : TypeToken<List<Course>>() {}.type
        return Gson().fromJson(value, listType)
    }
}
