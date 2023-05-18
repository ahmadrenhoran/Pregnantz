package com.ahmadrenhoran.pregnantz.ui.feature.article.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.ahmadrenhoran.pregnantz.R
import com.ahmadrenhoran.pregnantz.core.Utils
import com.ahmadrenhoran.pregnantz.domain.model.Article

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArticleItem(modifier: Modifier = Modifier, article: Article, onArticleClick: (String) -> Unit) {
    Card(modifier = modifier.fillMaxWidth(), onClick = { onArticleClick(article.url!!) }) {
        Row(
            modifier = Modifier
                .fillMaxSize()
        ) {
            AsyncImage(
                model = article.urlToImage ?: R.drawable.logo_light,
                contentDescription = "Profile Picture",
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .fillMaxSize()
                    .weight(0.4f)
                    .size(120.dp)
                    .background(color = Color.White),
                placeholder = painterResource(id = R.drawable.logo_light),
                alignment = Alignment.CenterStart,

                )
            Spacer(modifier = Modifier.width(8.dp))
            Column(
                modifier = Modifier
                    .weight(0.7f)
                    .padding(8.dp)
                    .fillMaxSize()
            ) {
                Text(modifier = Modifier.fillMaxSize(), text = article.title.toString())
                Spacer(modifier = Modifier.height(8.dp))
                Row(modifier = Modifier, verticalAlignment = Alignment.Bottom) {
                    AsyncImage(
                        model = Utils.getIconWebsite(article.url.toString())
                            ?: R.drawable.logo_light,
                        contentDescription = "Publisher Logo",
                        modifier = Modifier
                            .size(16.dp)
                            .padding(end = 4.dp)
                    )
                    Text(text = article.source?.name.toString(), fontSize = 12.sp)
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentWidth(Alignment.End),
                        text = Utils.parsingDateToSimpleFormat(article.publishedAt.toString()),
                        fontSize = 12.sp
                    )
                }
            }
        }
    }
}