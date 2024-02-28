package com.example.myapplication.dataclasses

import androidx.room.PrimaryKey

data class Course(
    @PrimaryKey(autoGenerate = true)
    val id: Int =0,
    var title: String,
    val logo: String,
    val name: String,

    )