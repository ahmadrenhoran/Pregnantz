package com.ahmadrenhoran.pregnantz.ui.feature.calculator

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ahmadrenhoran.pregnantz.core.Constants
import com.ahmadrenhoran.pregnantz.core.PregnancyUtils
import com.ahmadrenhoran.pregnantz.core.Utils
import com.ahmadrenhoran.pregnantz.ui.feature.form.component.DueDate
import java.time.LocalDate

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalculatorScreen(
    modifier: Modifier = Modifier,
    viewModel: CalculatorViewModel = hiltViewModel()
) {
    val uiState = viewModel.uiState.collectAsState().value
    LazyColumn(
        modifier = modifier
            .padding(12.dp)
    ) {
        item {
            Text(text = "Calculator", style = MaterialTheme.typography.displayLarge)
            DueDate(
                onDueDateChange = {
                    viewModel.setDate(it)
                    val weekList =
                        if (uiState.dueDateMenu.name == Constants.FIRST_DAY_OF_LAST_PERIOD) {
                            PregnancyUtils.getWeeksList(
                                PregnancyUtils.getFirstDayOfLastPeriodDueDate(
                                    LocalDate.parse(it)
                                )
                            )
                        } else {
                            PregnancyUtils.getWeeksList(it)
                        }
                    viewModel.setWeekList(weekList)
                },
                dueDate = uiState.date,
                dueDateMenu = uiState.dueDateMenu,
                dueDateMenuExpand = uiState.isDueDateMenuExpand,
                onExpandClick = { viewModel.setDueDateMenuExpand(true) },
                onDismissRequest = {
                    viewModel.setDueDateMenuExpand(false)
                },
                onMenuItemClick = {
                    viewModel.setDueDateMenu(it)
                    viewModel.setDueDateMenuExpand(false)
                    val weekList = if (it.name == Constants.FIRST_DAY_OF_LAST_PERIOD) {
                        PregnancyUtils.getWeeksList(
                            PregnancyUtils.getFirstDayOfLastPeriodDueDate(
                                LocalDate.parse(uiState.date)
                            )
                        )
                    } else {
                        PregnancyUtils.getWeeksList(uiState.date)
                    }
                    viewModel.setWeekList(weekList)
                }
            )
            Card(modifier = Modifier
                .fillMaxWidth(), onClick = { }) {
                Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                    Text(text = "Trimester 1")
                }
            }
        }


        itemsIndexed(uiState.weekList.subList(0, 12)) { index, item ->
            Text(text = "Week ${index + 1}: ${Utils.TimeFormatter(item)}")
        }

        item {
            Card(modifier = Modifier
                .fillMaxWidth(), onClick = { }) {
                Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                    Text(text = "Trimester 2")
                }
            }
        }

        itemsIndexed(uiState.weekList.subList(12, 27)) { index, item ->
            Text(text = "Week ${index + 13}: ${Utils.TimeFormatter(item)}")
        }


        item {
            Card(modifier = Modifier
                .fillMaxWidth(), onClick = { }) {
                Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                    Text(text = "Trimester 3")
                }
            }
        }

        itemsIndexed(uiState.weekList.subList(27, 42)) { index, item ->
            Text(text = "Week ${index + 28}: ${Utils.TimeFormatter(item)}")
        }


    }

}