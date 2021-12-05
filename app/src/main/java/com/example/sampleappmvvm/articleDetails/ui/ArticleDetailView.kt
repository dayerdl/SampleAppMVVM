package com.example.sampleappmvvm.articleDetails.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.text.font.FontWeight.Companion.Light
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sampleappmvvm.R
import com.example.sampleappmvvm.articleDetails.viewmodel.ArticleDetailsViewModel
import com.example.sampleappmvvm.server.ArticleDetails
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun ArticleDetailView(viewModel: ArticleDetailsViewModel, backHandler: () -> Unit) {
    val state = viewModel.viewModelData.observeAsState()
    state.value?.let { state ->
        val content = state as ArticleDetailsViewModel.State.Loaded
        val article = content.details.articleDetails
        Scaffold(topBar = {
            TopBarArticleDetails(
                state,
                { viewModel.saveFavorite(article) },
                backHandler
            )
        }) {
            ArticleDetailsBody(state)
        }
    }
}

@Composable
fun TopBarArticleDetails(
    state: ArticleDetailsViewModel.State,
    saveFavorite: () -> Unit,
    backHandler: () -> Unit
) {

    TopAppBar(
        title = { Text(text = "Article Details") },
        navigationIcon = {
            IconButton(onClick = { backHandler() }) {
                Icon(Icons.Filled.ArrowBack, "backIcon")
            }
        },
        actions = {
            Icon(
                getIcon(state), "fav",
                modifier = Modifier
                    .padding(end = 10.dp)
                    .clickable { saveFavorite() }
            )
        }
    )
}

fun getIcon(state: ArticleDetailsViewModel.State): ImageVector {
    state.let { content ->
        return when (content) {
            is ArticleDetailsViewModel.State.Loaded -> {
                if (content.details.favourite) {
                    Icons.Filled.Favorite
                } else {
                    Icons.Filled.FavoriteBorder
                }
            }
            else -> Icons.Filled.FavoriteBorder
        }
    }
}


@Composable
fun ArticleDetailsBody(state: ArticleDetailsViewModel.State) {
    state.let { content ->
        when (content) {
            is ArticleDetailsViewModel.State.Loaded -> {
                ArticleDetails(content.details.articleDetails)
            }
            else -> {}
        }
    }
}

@Composable
fun ArticleDetails(details: ArticleDetails) {
    val scrollState = rememberScrollState()

    Column(modifier = Modifier.verticalScroll(state = scrollState, enabled = true)) {
        GlideImage(
            imageModel = details.image_url,
            contentScale = ContentScale.FillBounds,
            placeHolder = ImageBitmap.imageResource(R.drawable.placeholder),
            modifier = Modifier
                .height(200.dp)
                .fillMaxWidth()
        )
        Column(
            modifier = Modifier
                .padding(start = 8.dp, end = 8.dp)
        ) {
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = details.title, fontSize = 17.sp, fontWeight = Bold)
            Spacer(modifier = Modifier.height(6.dp))
            Text(text = details.summary, fontSize = 13.sp, fontWeight = Light)
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = details.content, fontSize = 13.sp)
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Preview
@Composable
fun ArticleDetailPreview() {
    Scaffold(topBar = { TopBarArticleDetails(ArticleDetailsViewModel.State.NoAuth, { }, { }) }) {
        val detail = ArticleDetails(
            title = "This is a tile",
            summary = getMockText(),
            date = "",
            id = 1,
            thumbnail_template_url = "",
            thumbnail_url = "",
            content = "",
            image_url = "",
            source_url = ""
        )
        val mockDetails =
            ArticleDetailsViewModel.State.Loaded(ArticleDetailsModelView(detail, false))
        ArticleDetailsBody(mockDetails)
    }
}

fun getMockText(): String {
    return "Future Workshops has over a decade of experience building Apps and Platforms for " +
            "millions of people worldwide. We want to share our knowledge, connections and " +
            "expertise to help entrepreneurs grow their ideas and companies — so we started a " +
            "regular meetup which is open to everyone and includes pizza!\nOur meetup thrives" +
            " on the startups and speakers who have shared their inspirational stories. " +
            "In return, previous attendees found investment leads, business opportunities, " +
            "new sales, and received technical advice.\n— — — — — — — — — — — — — — — — — " +
            "— — — — — — — — — — — — —\nWe want you to succeed\nOur goal is to help you, " +
            "the startups. We want you to be direct, and tell us how we can help. For example, " +
            "you may be looking for investment, need focussed advice on how to conduct user" +
            " research, or want to go over your prototype or elevator pitch."
}