package com.example.myapplication.apidata

import com.example.myapplication.dataclasses.PathwaysResponse
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ApiService {
    @GET("pathways")
    suspend fun getPathways(): Response<PathwaysResponse>

    companion object {
        private const val BASE_URL = "https://merd-api.merakilearn.org/"

        fun create(): ApiService {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retrofit.create(ApiService::class.java)
        }
    }
}
