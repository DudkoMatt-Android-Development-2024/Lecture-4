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
import com.github.dudkomatt.androidcourse.dudkomatt_lecture4viewmodelinternet.model.Comment
import com.github.dudkomatt.androidcourse.dudkomatt_lecture4viewmodelinternet.model.Post
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

sealed interface HomeUiState {
    object Loading : HomeUiState
    object Error : HomeUiState
    data class Success(val posts: List<Post>, val comments: List<Comment>) : HomeUiState
}

class MainViewModel(private val jsonPlaceholderRepository: JsonPlaceholderRepository) :
    ViewModel() {
    var homeUiState: HomeUiState by mutableStateOf(HomeUiState.Loading)
        private set

    init {
        refresh()
    }

    fun refresh() {
        viewModelScope.launch {
            homeUiState = HomeUiState.Loading
            homeUiState = try {
                HomeUiState.Success(
                    jsonPlaceholderRepository.getPosts(),
                    jsonPlaceholderRepository.getComments()
                )
            } catch (_: IOException) {
                HomeUiState.Error
            } catch (_: HttpException) {
                HomeUiState.Error
            }
        }
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
