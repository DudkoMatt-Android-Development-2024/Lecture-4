package com.github.dudkomatt.androidcourse.dudkomatt_lecture4viewmodelinternet.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.github.dudkomatt.androidcourse.dudkomatt_lecture4viewmodelinternet.PostsApplication
import com.github.dudkomatt.androidcourse.dudkomatt_lecture4viewmodelinternet.data.JsonPlaceholderRepository
import com.github.dudkomatt.androidcourse.dudkomatt_lecture4viewmodelinternet.model.Post
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

sealed interface PostsUiState {
    data class Success(val posts: List<Post>) : PostsUiState
    object Error : PostsUiState
    object Loading : PostsUiState
}

class PostsViewModel(private val jsonPlaceholderRepository: JsonPlaceholderRepository) :
    ViewModel() {
    var postsUiState: PostsUiState by mutableStateOf(PostsUiState.Loading)
        private set

    init {
        getPosts()
    }

    fun getPosts() {
        viewModelScope.launch {
            postsUiState = PostsUiState.Loading
            postsUiState = try {
                PostsUiState.Success(jsonPlaceholderRepository.getPosts())
            } catch (_: IOException) {
                PostsUiState.Error
            } catch (_: HttpException) {
                PostsUiState.Error
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as PostsApplication)
                val jsonPlaceholderRepository = application.container.jsonPlaceholderRepository
                PostsViewModel(jsonPlaceholderRepository = jsonPlaceholderRepository)
            }
        }
    }
}

//data class PostsUIState(
//    val posts: List<Post> = emptyList(),
//    val loading: Boolean = false,
//    val error: String? = null,
//)
