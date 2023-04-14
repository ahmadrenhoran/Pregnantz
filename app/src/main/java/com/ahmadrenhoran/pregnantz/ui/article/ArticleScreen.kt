package com.ahmadrenhoran.pregnantz.ui.article

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ahmadrenhoran.pregnantz.ui.article.component.GetArticlesResponse

@Composable
fun ArticleScreen(modifier: Modifier = Modifier, viewModel: ArticleViewModel = hiltViewModel()) {

    Column(modifier = modifier.padding(16.dp)) {
        Text(text = "Articles", fontSize = 24.sp, color = MaterialTheme.colorScheme.onBackground)
        Spacer(modifier = modifier.padding(8.dp))
        GetArticlesResponse()
    }
}