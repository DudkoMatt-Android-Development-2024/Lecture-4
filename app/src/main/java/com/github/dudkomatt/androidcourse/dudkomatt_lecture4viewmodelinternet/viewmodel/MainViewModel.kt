package com.github.dudkomatt.androidcourse.dudkomatt_lecture4viewmodelinternet.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.github.dudkomatt.androidcourse.dudkomatt_lecture4viewmodelinternet.PostsApplication
import com.github.dudkomatt.androidcourse.dudkomatt_lecture4viewmodelinternet.data.JsonPlaceholderRepository
import com.github.dudkomatt.androidcourse.dudkomatt_lecture4viewmodelinternet.model.Comment
import com.github.dudkomatt.androidcourse.dudkomatt_lecture4viewmodelinternet.model.Post
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

sealed interface LoadingState {
    data object Loading : LoadingState
    data object Error : LoadingState
    data object Success : LoadingState
}

data class HomeUiState(
    var posts: Map<Int, Post>? = null,
    var comments: Map<Int, List<Comment>>? = null,
    var state: LoadingState = LoadingState.Loading,
    var activePostId: Int? = null
)

class MainViewModel(private val jsonPlaceholderRepository: JsonPlaceholderRepository) :
    ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    init {
        refresh()
    }

    fun refresh() {
        viewModelScope.launch {
            _uiState.value = HomeUiState()
            try {
                _uiState.value = HomeUiState(
                    posts = jsonPlaceholderRepository.getPosts().associateBy { it.id },
                    comments = jsonPlaceholderRepository.getComments().groupBy { it.postId },
                    state = LoadingState.Success
                )
            } catch (_: IOException) {
                _uiState.value = HomeUiState(state = LoadingState.Error)
            } catch (_: HttpException) {
                _uiState.value = HomeUiState(state = LoadingState.Error)
            }
        }
    }

    fun openPostDetails(postId: Int) {
        _uiState.value = _uiState.value.copy(activePostId = postId)
    }

    fun closePostDetails() {
        _uiState.value = _uiState.value.copy(activePostId = null)
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as PostsApplication)
                val jsonPlaceholderRepository = application.container.jsonPlaceholderRepository
                MainViewModel(jsonPlaceholderRepository = jsonPlaceholderRepository)
            }
        }
    }
}
