package com.github.dudkomatt.androidcourse.dudkomatt_lecture4viewmodelinternet.ui.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.github.dudkomatt.androidcourse.dudkomatt_lecture4viewmodelinternet.model.Post
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun HomeScreen(
    posts: List<Post>
) {
    Surface {
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            items(posts, key = Post::id) {
                PostRow(
                    modifier = Modifier
                        .padding(16.dp),
                    post = it
                )
            }
        }
    }
}

@Composable
fun PostRow(
    post: Post,
    modifier: Modifier = Modifier
) {
    val commonPadding = PaddingValues(8.dp)

    Surface(
        modifier = modifier,
        tonalElevation = 16.dp,
        shadowElevation = 8.dp,
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.onSurface)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                modifier = Modifier.padding(commonPadding),
                text = "Post #${post.id}",
                textAlign = TextAlign.Start,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.headlineSmall
            )
            HorizontalDivider(
                modifier = Modifier.padding(commonPadding),
            )
            Text(
                modifier = Modifier.padding(commonPadding),
                text = post.title,
                textAlign = TextAlign.Start,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.headlineSmall
            )
            Text(
                modifier = Modifier.padding(commonPadding),
                text = post.body,
                textAlign = TextAlign.Start,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PostRowPreview() {
    PostRow(Post(1, 2, "Title", "Body"))
}

@Preview(showBackground = true)
@Composable
fun PostRowLongTextPreview() {
    PostRow(
        Post(
            1,
            2,
            "Title that contains a very very long text that absolutely will not fit into the title",
            "What is Lorem Ipsum? Lorem Ipsum is simply dummy text of the printing and typesetting " +
                    "industry. Lorem Ipsum has been the industry's standard dummy text ever since " +
                    "the 1500s, when an unknown printer took a galley of type and scrambled it to " +
                    "make a type specimen book. It has survived not only five centuries, but also " +
                    "the leap into electronic typesetting, remaining essentially unchanged. It was " +
                    "popularised in the 1960s with the release of Letraset sheets containing Lorem " +
                    "Ipsum passages, and more recently with desktop publishing software like " +
                    "Aldus PageMaker including versions of Lorem Ipsum."
        )
    )
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen(
        List(20) { idx ->
            Post(idx, idx, "Title $idx", "Body $idx")
        }
    )
}
