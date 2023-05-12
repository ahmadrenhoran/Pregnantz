package com.ahmadrenhoran.pregnantz.ui.feature.home.component

import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarContent(
    modifier: Modifier = Modifier,
    name: String,
    photoUrl: String,
    onProfileClick: () -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Column() {
            Text(text = "Welcome!")
            Text(text = "${name}", style = MaterialTheme.typography.displaySmall)
        }
        Card(
            onClick = onProfileClick, modifier = Modifier
                .size(38.dp)
                .clip(CircleShape)
                .background(color = Color.Transparent)
        ) {
            AsyncImage(
                model = photoUrl,
                contentDescription = "Profile Picture",
                modifier = Modifier
                    .background(color = Color.White),
                contentScale = ContentScale.Crop
            )
        }

    }
}