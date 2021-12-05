package com.example.sampleappmvvm.articlesList.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LiveData
import com.example.sampleappmvvm.R
import com.example.sampleappmvvm.articlesList.viewmodel.ArticlesListViewModel
import com.example.sampleappmvvm.server.ArticleListItem
import com.example.sampleappmvvm.views.CircularProgressBar

@Composable
fun ArticlesList(
    state: LiveData<ArticlesListViewModel.State>,
    logout: () -> Unit,
    itemClick: (ArticleListItem) -> Unit
) {
    val state = state.observeAsState()
    var showMenu by remember { mutableStateOf(false) }

    Scaffold(topBar = {
        TopAppBar(
            title = { Text(text = "Articles") },
            actions = {
                Icon(
                    Icons.Filled.MoreVert, "Log Out",
                    modifier = Modifier
                        .padding(end = 10.dp)
                        .clickable { showMenu = !showMenu }
                )

                DropdownMenu(
                    expanded = showMenu,
                    onDismissRequest = { showMenu = false }
                ) {
                    DropdownMenuItem(onClick = { logout() }) {
                        Text(text = "Log out")
                    }
                }
            }
        )
    }) {
        when (state.value) {
            is ArticlesListViewModel.State.Loaded -> {
                val articles = (state.value as ArticlesListViewModel.State.Loaded).articles
                ListOfArticles(articles = articles, itemClick)
            }
            is ArticlesListViewModel.State.Loading -> CircularProgressBar()
        }
    }
}

@Composable
fun ListOfArticles(articles: List<ArticleListItem>, onItemClicked: (ArticleListItem) -> Unit) {
    LazyColumn(verticalArrangement = Arrangement.spacedBy(4.dp)) {
        items(articles) { article ->
            ArticleRow(article = article) { onItemClicked(article) }
        }
    }
}

@Composable
fun ArticleRow(article: ArticleListItem, itemClick: () -> Unit) {
    Row(
        Modifier
            .height(IntrinsicSize.Min)
            .width(IntrinsicSize.Max)
            .padding(start = 16.dp, end = 10.dp, top = 10.dp, bottom = 10.dp)
            .clickable { itemClick.invoke() }
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_foreground),
                contentDescription = "article image", Modifier.size(50.dp)
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(text = article.date, fontSize = 10.sp)
        }
        Spacer(modifier = Modifier.width(15.dp))
        Box(modifier = Modifier.fillMaxHeight()) {
            Column() {
                Text(text = article.title, fontSize = 16.sp, maxLines = 1)
                Spacer(modifier = Modifier.height(10.dp))
                Text(text = article.summary, fontSize = 13.sp)
                Spacer(modifier = Modifier.height(25.dp))
            }
            Divider(
                color = Color.LightGray,
                thickness = 1.dp,
                modifier = Modifier.align(Alignment.BottomEnd)
            )
        }
    }
}

@Preview
@Composable
fun PreviewArticleRow() {
    ArticleRow(getMockArticles().first()) {}
}

@Preview
@Composable
fun PreviewArticles() {
    LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        for (index in 0..1) {
            item {
                ArticleRow(getMockArticles()[index]) {}
            }
        }
    }
}

fun getMockArticles(): List<ArticleListItem> {
    val list = arrayListOf<ArticleListItem>()
    val article1 = ArticleListItem(
        date = "2019-05-15",
        id = 127,
        summary = "Apps4Startups is an event for entrepreneurs to discuss, share, pitch and network, run by Future Workshops.",
        thumbnail_template_url = "https://miro.medium.com/fit/c/:width/:height/1*hFIdXmRlY278dzunhO5Mew.png",
        thumbnail_url = "https://miro.medium.com/fit/c/360/360/1*hFIdXmRlY278dzunhO5Mew.png",
        title = "Speaking at Apps4Startups"
    )
    val article2 = ArticleListItem(
        date = "2016-09-06",
        id = 2143,
        summary = "Apple’s latest mobile releases — iOS 10 and watchOS 3 — provide clear opportunities to integrate Apps with the iPhone, iPad and Apple Watch at a fundamental level.",
        thumbnail_template_url = "https://miro.medium.com/fit/c/:width/:height/1*14aRtdZGuYj_VMVl9Mxcwg.jpeg",
        thumbnail_url = "https://miro.medium.com/fit/c/360/360/1*14aRtdZGuYj_VMVl9Mxcwg.jpeg",
        title = "How Apps will evolve with iOS 10"
    )
    list.add(article1)
    list.add(article2)
    return list
}

