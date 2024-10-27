package com.github.dudkomatt.androidcourse.dudkomatt_lecture4viewmodelinternet.data

import com.github.dudkomatt.androidcourse.dudkomatt_lecture4viewmodelinternet.model.Comment
import com.github.dudkomatt.androidcourse.dudkomatt_lecture4viewmodelinternet.model.Post
import com.github.dudkomatt.androidcourse.dudkomatt_lecture4viewmodelinternet.network.JsonPlaceholderApiService

interface JsonPlaceholderRepository {
    suspend fun getPosts(): List<Post>
    suspend fun getComments(): List<Comment>
}

class NetworkJsonPlaceholderRepository(
    private val jsonPlaceholderApiService: JsonPlaceholderApiService
) : JsonPlaceholderRepository {
    override suspend fun getPosts(): List<Post> = jsonPlaceholderApiService.getPosts()
    override suspend fun getComments(): List<Comment> = jsonPlaceholderApiService.getComments()
}
