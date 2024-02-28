package com.example.myapplication.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.myapplication.dataclasses.itemDataclass

@Dao
interface Daoclas {
    @Query("SELECT * FROM items")
    suspend fun getAllItems(): List<itemDataclass>

    @Insert
    suspend fun insertItem(item: itemDataclass)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(items: List<itemDataclass>)
}
