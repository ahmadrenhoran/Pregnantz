package com.ahmadrenhoran.pregnantz.ui.feature.home

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.ahmadrenhoran.pregnantz.R
import com.ahmadrenhoran.pregnantz.core.Constants


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(viewModel: HomeViewModel = hiltViewModel(), onProfileClick: () -> Unit) {
    val uiState = viewModel.uiState.collectAsState().value
    Log.d(Constants.ALL_TAG, "HomeScreen: ${uiState.photoUrl}")
    Scaffold(modifier = Modifier.padding(16.dp),
        topBar = {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Column() {
                    Text(text = "Welcome!")
                    Text(text = "${viewModel.displayName}", style = MaterialTheme.typography.displaySmall)
                }
                Card(
                    onClick = onProfileClick, modifier = Modifier
                        .size(38.dp)
                        .clip(CircleShape)
                        .background(color = Color.Transparent)
                ) {
                    AsyncImage(
                        model = viewModel.photoUrl ?: R.drawable.logo_light,
                        contentDescription = "Profile Picture",
                        modifier = Modifier
//                            .size(128.dp)
//                            .clip(CircleShape)
                            .background(color = Color.White),
                        contentScale = ContentScale.Crop
                    )
                }

            }
        }
    ) { innerPadding ->
        Text(modifier = Modifier.padding(innerPadding), text = "")

    }
}