package com.ahmadrenhoran.pregnantz.ui.feature.home

import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Remove
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.ahmadrenhoran.pregnantz.domain.model.Response
import com.ahmadrenhoran.pregnantz.ui.feature.home.component.DueDateView
import com.ahmadrenhoran.pregnantz.ui.feature.home.component.ExerciseView
import com.ahmadrenhoran.pregnantz.ui.feature.home.component.NutritionView
import java.time.DayOfWeek
import java.time.LocalDate


@Composable
fun HomeScreen(viewModel: HomeViewModel = hiltViewModel(), onProfileClick: () -> Unit) {
    val uiState = viewModel.uiState.collectAsState().value
    when (val response = viewModel.getUserDataResponse) {
        is Response.Loading -> {}
        is Response.Success -> response.data.let {
            LaunchedEffect(it) {
                viewModel.setUser(it)
            }
        }
        is Response.Failure -> {}
    }
    Scaffold(modifier = Modifier.padding(16.dp),
        topBar = {
            TopBarContent(name = uiState.user.name, photoUrl = uiState.user.photoUrl, onProfileClick = onProfileClick)
        }
    ) { innerPadding ->
        Column(modifier = Modifier
            .padding(innerPadding)
            .verticalScroll(rememberScrollState()), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            DueDateView(dueDate = LocalDate.parse(uiState.user.dueDate), currentWeek = viewModel.currentWeek, daysToGo = viewModel.currentDays, trimester = viewModel.trimester)
            NutritionView()
            ExerciseView()

        }

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarContent(modifier: Modifier = Modifier,name: String, photoUrl: String, onProfileClick: () -> Unit) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
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