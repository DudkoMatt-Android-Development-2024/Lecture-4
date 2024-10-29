package com.github.dudkomatt.androidcourse.dudkomatt_lecture4viewmodelinternet.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.github.dudkomatt.androidcourse.dudkomatt_lecture4viewmodelinternet.R
import com.github.dudkomatt.androidcourse.dudkomatt_lecture4viewmodelinternet.ui.screen.ErrorScreen
import com.github.dudkomatt.androidcourse.dudkomatt_lecture4viewmodelinternet.ui.screen.HomeScreen
import com.github.dudkomatt.androidcourse.dudkomatt_lecture4viewmodelinternet.ui.screen.LoadingScreen
import com.github.dudkomatt.androidcourse.dudkomatt_lecture4viewmodelinternet.ui.screen.PostDetailsScreen
import com.github.dudkomatt.androidcourse.dudkomatt_lecture4viewmodelinternet.viewmodel.HomeUiState
import com.github.dudkomatt.androidcourse.dudkomatt_lecture4viewmodelinternet.viewmodel.LoadingState

@Composable
fun PostsApp(
    uiState: HomeUiState,
    refreshFunction: () -> Unit,
    closePostDetailsFunction: () -> Unit,
    postSelectedFunction: (Int) -> Unit
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        floatingActionButton = {
            if (uiState.state != LoadingState.Loading) {
                FloatingActionButton(onClick = refreshFunction) {
                    Icon(
                        Icons.Default.Refresh,
                        contentDescription = stringResource(R.string.refresh_floating_action_button_description)
                    )
                }
            }
        }
    ) { innerPadding ->
        Surface(modifier = Modifier.padding(innerPadding)) {
            if (uiState.activePostId != null) {
                PostDetailsScreen(
                    uiState,
                    closePostDetailsFunction
                )
            } else {
                MainScreen(uiState, postSelectedFunction)
            }
        }
    }
}

@Composable
fun MainScreen(
    uiState: HomeUiState,
    postSelectedFunction: (Int) -> Unit
) {
    when (uiState.state) {
        LoadingState.Loading -> LoadingScreen()
        LoadingState.Error -> ErrorScreen()
        LoadingState.Success -> HomeScreen(
            uiState.posts?.values?.toList() ?: listOf(),
            postSelectedFunction = postSelectedFunction
        )
    }
}
