package com.ahmadrenhoran.pregnantz.ui.feature.article.component

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ahmadrenhoran.pregnantz.domain.model.Response
import com.ahmadrenhoran.pregnantz.ui.component.ProgressBar
import com.ahmadrenhoran.pregnantz.ui.feature.article.ArticleViewModel

@Composable
fun GetArticlesResponse(viewModel: ArticleViewModel = hiltViewModel(), context: Context) {
    val uiState = viewModel.uiState.collectAsState()
    when(val response = viewModel.getArticleResponse) {
        is Response.Loading -> ProgressBar()
        is Response.Success -> response.data.let { articles ->
            viewModel.setArticles(articles = articles)
            LazyColumn{
                items(uiState.value.articles) { article ->
                    ArticleItem(article = article, onArticleClick = {
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(it))
                        context.startActivity(intent)
                    })
                    Spacer(modifier = Modifier.height(12.dp))
                }
            }
        }
        is Response.Failure -> response.apply {
            var errorDesc by remember { mutableStateOf("") }
            LaunchedEffect(e) {
                errorDesc = e.localizedMessage
            }
            Box(modifier = Modifier
                .fillMaxSize()
                .wrapContentSize(Alignment.Center)) {
                Text(text = errorDesc)
            }
        }
    }
}
