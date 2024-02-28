package com.example.myapplication.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.myapplication.dataclasses.itemDataclass

@Database(entities = [itemDataclass::class], version = 3)
abstract class Appdatabse: RoomDatabase() {
    abstract fun itemDao(): Daoclas
}
