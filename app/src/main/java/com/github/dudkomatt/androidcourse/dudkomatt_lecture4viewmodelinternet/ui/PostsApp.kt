package com.github.dudkomatt.androidcourse.dudkomatt_lecture4viewmodelinternet.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.github.dudkomatt.androidcourse.dudkomatt_lecture4viewmodelinternet.R
import com.github.dudkomatt.androidcourse.dudkomatt_lecture4viewmodelinternet.ui.screen.ErrorScreen
import com.github.dudkomatt.androidcourse.dudkomatt_lecture4viewmodelinternet.ui.screen.HomeScreen
import com.github.dudkomatt.androidcourse.dudkomatt_lecture4viewmodelinternet.ui.screen.LoadingScreen
import com.github.dudkomatt.androidcourse.dudkomatt_lecture4viewmodelinternet.viewmodel.HomeUiState

@Composable
fun PostsApp(
    uiState: HomeUiState,
    refreshFunction: () -> Unit
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        floatingActionButton = {
            if (uiState != HomeUiState.Loading) {
                FloatingActionButton(onClick = refreshFunction) {
                    Icon(
                        Icons.Default.Refresh,
                        contentDescription = stringResource(R.string.refresh_floating_action_button_description)
                    )
                }
            }
        }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            when (uiState) {
                HomeUiState.Loading -> LoadingScreen()
                HomeUiState.Error -> ErrorScreen()
                is HomeUiState.Success -> HomeScreen(uiState.posts)
            }
        }
    }
}
