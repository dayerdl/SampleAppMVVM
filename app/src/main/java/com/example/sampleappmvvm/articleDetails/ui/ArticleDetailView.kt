package com.example.sampleappmvvm.articleDetails.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.text.font.FontWeight.Companion.Light
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sampleappmvvm.R
import com.example.sampleappmvvm.articleDetails.viewmodel.ArticleDetailsViewModel
import com.example.sampleappmvvm.server.ArticleListItem

@Composable
fun ArticleDetailView(viewModel: ArticleDetailsViewModel) {
    val state = viewModel.viewModelData.observeAsState()
    state.value?.let { it ->
        val content = it as ArticleDetailsViewModel.State.Loaded
        val article = content.details
        Scaffold(topBar = { TopBarArticleDetails(viewModel.saveFavorite(article)) }) {
            state.value?.let {
                ArticleDetailsBody(it)
            }
        }
    }
}

@Composable
fun TopBarArticleDetails(saveFavorite: Unit) {

    TopAppBar(
        title = { Text(text = "Article Details") },
        navigationIcon = {
            IconButton(onClick = {}) {
                Icon(Icons.Filled.ArrowBack, "backIcon")
            }
        },
        actions = {
            Icon(
                Icons.Filled.Favorite, "fav",
                modifier = Modifier
                    .padding(end = 10.dp)
                    .clickable { saveFavorite }
            )
        }
    )
}

@Composable
fun ArticleDetailsBody(state: ArticleDetailsViewModel.State) {
    state.let { content ->
        when (content) {
            is ArticleDetailsViewModel.State.Loaded -> {
                ArticleDetails(content.details)
            }
        }
    }
}

@Composable
fun ArticleDetails(details: ArticleListItem) {
    Column {
        Image(
            painter = painterResource(id = R.drawable.placeholder),
            contentDescription = "placeholder",
            modifier = Modifier
                .height(200.dp)
                .fillMaxWidth(),
            contentScale = ContentScale.FillBounds
        )
        Column(modifier = Modifier.padding(start = 8.dp, end = 8.dp)) {
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = details.title, fontSize = 17.sp, fontWeight = Bold)
            Text(text = "TODO when BE is up", fontSize = 13.sp, fontWeight = Light)
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = details.summary, fontSize = 13.sp)
        }
    }
}

@Preview
@Composable
fun ArticleDetailPreview() {
    Scaffold(topBar = { TopBarArticleDetails(Unit) }) {
        val detail = ArticleListItem(title = "This is a tile", summary = getMockText(), date = "", id = 1,
            thumbnail_template_url = "", thumbnail_url = "", favourite = false)
        val mockDetails = ArticleDetailsViewModel.State.Loaded(detail)
        ArticleDetailsBody(mockDetails)
    }
}

fun getMockText(): String {
    return "asld asldkmasldmka l akmdlakmdlakmsdlakmsd alkdm laksdm lamdlkamldk malsdk malskdmlaks" +
            "kansdkjasndkasndkjanskdjnaksdjnkasjndkajnsdkjasnkdjanskdjanskjdna" +
            "askdjnaskjndkasjnkdjansdkjasndkjanskdjnsadk;jnaskdjnaskjdn"
}