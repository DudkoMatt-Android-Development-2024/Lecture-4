package com.github.dudkomatt.androidcourse.dudkomatt_lecture4viewmodelinternet.ui

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
import com.github.dudkomatt.androidcourse.dudkomatt_lecture4viewmodelinternet.viewmodel.PostsUiState
import com.github.dudkomatt.androidcourse.dudkomatt_lecture4viewmodelinternet.viewmodel.PostsViewModel

@Composable
fun PostsApp(
    viewModel: PostsViewModel
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        floatingActionButton = {
            FloatingActionButton(onClick = {/*TODO*/ }) {
                Icon(
                    Icons.Default.Refresh,
                    contentDescription = stringResource(R.string.refresh_floating_action_button_description)
                )
            }
        }
    ) { innerPadding ->

        when (viewModel.postsUiState) {
            PostsUiState.Error -> Text("Error")
            PostsUiState.Loading -> Text("Loading")
            is PostsUiState.Success -> Text("Success")
        }

//        LazyColumn (
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(innerPadding)
//        ) {
//
//        }
    }
}
