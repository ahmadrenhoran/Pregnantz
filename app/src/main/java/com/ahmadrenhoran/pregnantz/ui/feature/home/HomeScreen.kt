package com.ahmadrenhoran.pregnantz.ui.feature.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Remove
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ahmadrenhoran.pregnantz.domain.model.Response
import com.ahmadrenhoran.pregnantz.ui.feature.home.component.*
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
    Scaffold(modifier = Modifier.padding(14.dp),
        topBar = {
            TopBarContent(
                name = uiState.user.name,
                photoUrl = uiState.user.photoUrl,
                onProfileClick = onProfileClick
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {

            FetalDevelopmentContent(modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, top = 12.dp), viewModel.babyData, viewModel.currentWeek)
            DueDateView(
                dueDate = LocalDate.parse(uiState.user.dueDate),
                currentWeek = viewModel.currentWeek,
                daysToGo = viewModel.currentDays,
                trimester = viewModel.trimester
            )
            HealthyTipsView(whatToDo = viewModel.whatToDo, whatToAvoid = viewModel.whatToAvoid)
            NutritionView()
            ExerciseView()

        }

    }
}

@Composable
fun HealthyTipsView(modifier: Modifier = Modifier, whatToDo: String, whatToAvoid: String) {
    var showToDo by remember {
        mutableStateOf(false)
    }

    var showToAvoid by remember {
        mutableStateOf(false)
    }
    Card(modifier = modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(12.dp)) {
            Text(text = "Healthy Tips This Trimester", style = MaterialTheme.typography.displaySmall)
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                Text(text = "What To Do", style = MaterialTheme.typography.displaySmall, fontSize = 16.sp)
                IconButton(onClick = { showToDo = !showToDo }) {
                    if (!showToDo) {
                        Icon(
                            imageVector = Icons.Outlined.Add,
                            contentDescription = "Show More",
                        )
                    } else {
                        Icon(
                            imageVector = Icons.Outlined.Remove,
                            contentDescription = "Show More",
                        )
                    }
                }
            }
            AnimatedVisibility(visible= showToDo) {
                Text(text = whatToDo)
            }
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {

                Text(text = "What to avoid", style = MaterialTheme.typography.displaySmall, fontSize = 16.sp)
                IconButton(onClick = { showToAvoid = !showToAvoid }) {
                    if (!showToAvoid) {
                        Icon(
                            imageVector = Icons.Outlined.Add,
                            contentDescription = "Show More",
                        )
                    } else {
                        Icon(
                            imageVector = Icons.Outlined.Remove,
                            contentDescription = "Show More",
                        )
                    }
                }
            }
            AnimatedVisibility(visible= showToAvoid) {
                Text(text = whatToAvoid)
            }

        }
    }
}

