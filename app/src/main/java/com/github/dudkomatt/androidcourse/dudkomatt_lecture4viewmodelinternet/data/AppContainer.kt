package com.github.dudkomatt.androidcourse.dudkomatt_lecture4viewmodelinternet.data

import com.github.dudkomatt.androidcourse.dudkomatt_lecture4viewmodelinternet.network.JsonPlaceholderApiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import retrofit2.Retrofit

import okhttp3.MediaType.Companion.toMediaType

interface AppContainer {
    val jsonPlaceholderRepository: JsonPlaceholderRepository
}

class DefaultAppContainer : AppContainer {
    private val baseUrl = "https://jsonplaceholder.typicode.com"

    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(baseUrl)
        .build()

    private val jsonPlaceholderRetrofitService: JsonPlaceholderApiService by lazy {
        retrofit.create(JsonPlaceholderApiService::class.java)
    }

    override val jsonPlaceholderRepository: JsonPlaceholderRepository by lazy {
        NetworkJsonPlaceholderRepository(jsonPlaceholderRetrofitService)
    }
}
