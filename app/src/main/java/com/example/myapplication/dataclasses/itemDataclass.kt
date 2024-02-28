package com.example.myapplication.dataclasses

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.myapplication.apidata.Converters

@Entity(tableName = "items")
@TypeConverters(Converters::class)
data class itemDataclass(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    var title: String,
    var logo: String,
    val name: String,
    val courses: List<Course>
)

data class PathwaysResponse(val pathways: List<itemDataclass>)
