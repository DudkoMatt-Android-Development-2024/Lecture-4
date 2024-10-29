package com.github.dudkomatt.androidcourse.dudkomatt_lecture4viewmodelinternet

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.github.dudkomatt.androidcourse.dudkomatt_lecture4viewmodelinternet.ui.PostsApp
import com.github.dudkomatt.androidcourse.dudkomatt_lecture4viewmodelinternet.ui.theme.DudkoMattLecture4ViewModelInternetTheme
import com.github.dudkomatt.androidcourse.dudkomatt_lecture4viewmodelinternet.viewmodel.MainViewModel

// Inspired by https://developer.android.com/courses/pathways/android-basics-compose-unit-5-pathway-1
class MainActivity : ComponentActivity() {
    private val vm: MainViewModel by viewModels<MainViewModel>(factoryProducer = { MainViewModel.Factory })

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DudkoMattLecture4ViewModelInternetTheme {
                val uiState by vm.uiState.collectAsState()
                PostsApp(
                    uiState = uiState,
                    refreshFunction = vm::refresh,
                    closePostDetailsFunction = vm::closePostDetails,
                    postSelectedFunction = vm::openPostDetails
                )
            }
        }
    }
}
