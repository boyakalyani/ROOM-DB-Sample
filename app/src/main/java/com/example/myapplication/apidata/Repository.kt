package com.example.myapplication.apidata

import android.util.Log
import com.example.myapplication.dataclasses.itemDataclass
import com.example.myapplication.db.Daoclas

class Repository(private val apiService: ApiService, private val itemDao: Daoclas) {
    suspend fun fetchDataAndInsertIntoDb() {
        try {
            val response = apiService.getPathways()
            if (response.isSuccessful) {
                val pathways = response.body()?.pathways ?: emptyList()
                ;
                val courses = pathways.flatMap { pathway -> pathway.courses }
                val items = courses.map { course ->
                    itemDataclass(
                        title = course.name,
                        logo = course.logo.toString(),
                        name = "",
                        courses = listOf(course)
                    )
                }
                itemDao.insertAll(items)
            } else {
                Log.e("Repository", "fetchDataAndInsertIntoDb: ${response.errorBody()}")
            }
        }
        catch (e: Exception) {
            Log.e("Repository", "fetchDataAndInsertIntoDb: ${e.message}")
        }
    }
}



