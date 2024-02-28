package com.example.myapplication.apidata

import com.example.myapplication.dataclasses.itemDataclass
import com.example.myapplication.db.Daoclas

class Repository(private val apiService: ApiService, private val itemDao: Daoclas) {
    suspend fun fetchDataAndInsertIntoDb() {
        val response = apiService.getPathways()
        if (response.isSuccessful) {
            val pathways = response.body()?.pathways ?: emptyList()
   ;         val courses = pathways.flatMap { pathway -> pathway.courses }
            val items = courses.map { course ->
                itemDataclass(title = course.name, logo = course.logo.toString(), name = "", courses = listOf(course))
            }
            itemDao.insertAll(items)
        } else {
            // Handle error].
        }
    }
}


