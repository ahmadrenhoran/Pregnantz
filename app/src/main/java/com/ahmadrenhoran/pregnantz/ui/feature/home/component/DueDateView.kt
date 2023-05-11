package com.ahmadrenhoran.pregnantz.ui.feature.home.component

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ahmadrenhoran.pregnantz.core.Utils
import java.time.LocalDate

@Composable
fun DueDateView(modifier: Modifier = Modifier, dueDate: LocalDate, currentWeek: Int, daysToGo: Int, trimester: String) {
    Card(modifier = modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(12.dp)) {
            Text(text = "Week $currentWeek ($daysToGo days to go)", style = MaterialTheme.typography.displaySmall)
            Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
                Text(text = "$trimester")
                Text(text = "Due ${Utils.TimeFormatter(dueDate)}")
            }
        }
    }
}