package com.github.dudkomatt.androidcourse.dudkomatt_lecture4viewmodelinternet.network

import com.github.dudkomatt.androidcourse.dudkomatt_lecture4viewmodelinternet.model.Comment
import com.github.dudkomatt.androidcourse.dudkomatt_lecture4viewmodelinternet.model.Post
import retrofit2.http.GET

interface JsonPlaceholderApiService {
    @GET("/posts")
    suspend fun getPosts(): List<Post>

    @GET("/comments")
    suspend fun getComments(): List<Comment>
}
