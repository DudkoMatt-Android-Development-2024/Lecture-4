package com.github.dudkomatt.androidcourse.dudkomatt_lecture4viewmodelinternet.ui.screen

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.dudkomatt.androidcourse.dudkomatt_lecture4viewmodelinternet.R
import com.github.dudkomatt.androidcourse.dudkomatt_lecture4viewmodelinternet.model.Comment
import com.github.dudkomatt.androidcourse.dudkomatt_lecture4viewmodelinternet.model.Post
import com.github.dudkomatt.androidcourse.dudkomatt_lecture4viewmodelinternet.viewmodel.HomeUiState
import com.github.dudkomatt.androidcourse.dudkomatt_lecture4viewmodelinternet.viewmodel.LoadingState

@Composable
fun PostDetailsScreen(
    uiState: HomeUiState,
    closeDetailsScreen: () -> Unit
) {
    BackHandler {
        closeDetailsScreen()
    }

    val activePostId = uiState.activePostId
    if (activePostId != null) {

        Column(modifier = Modifier.fillMaxSize()) {
            val post: Post? = uiState.posts?.get(activePostId)
            val comments: List<Comment>? = uiState.comments?.get(activePostId)

            if (post != null) {
                PostInfo(post)
            }
            HorizontalDivider()
            if (comments != null) {
                LazyColumn {
                    items(comments) {
                        CommentRow(it)
                        HorizontalDivider()
                    }
                }
            }
        }
    }
}

@Composable
fun PostInfo(post: Post) {
    val commonPadding = PaddingValues(8.dp)
    Surface(
        tonalElevation = 48.dp
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(commonPadding)
        ) {
            Text(
                text = stringResource(R.string.post_id, post.id),
                textAlign = TextAlign.Start,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.headlineSmall
            )
            BoldPrefixSpanText(stringResource(R.string.userid), post.userId.toString())
            BoldPrefixSpanText(stringResource(R.string.post_title), post.title)
            BoldPrefixSpanText(stringResource(R.string.body), post.body)
        }
    }
}

@Composable
fun CommentRow(comment: Comment) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        BoldPrefixSpanText(stringResource(R.string.commentid), comment.id.toString())
        BoldPrefixSpanText(stringResource(R.string.user_email), comment.email)
        BoldPrefixSpanText(stringResource(R.string.comment_title), comment.name)
        BoldPrefixSpanText(stringResource(R.string.comment))
        Text(text = comment.body)
    }
}

const val TEXT_DELIMITER = ":"

@Composable
fun BoldPrefixSpanText(
    prefix: String,
    content: String? = null
) {
    Text(
        buildAnnotatedString {
            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                append(prefix + TEXT_DELIMITER)
            }
            if (content != null) {
                append(" $content")
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun PostDetailsScreenPreview() {
    PostDetailsScreen(
        HomeUiState(
            posts = mapOf(1 to Post(1, 1, "Title", "Post body")),
            comments = mapOf(1 to listOf(Comment(1, 1, "String", "email", "body"))),
            state = LoadingState.Success,
            activePostId = 1
        )
    ) {}
}
