package com.ahmadrenhoran.pregnantz.ui.feature.form.component

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ahmadrenhoran.pregnantz.core.Constants
import com.ahmadrenhoran.pregnantz.core.DueDateMenu
import com.ahmadrenhoran.pregnantz.core.Utils
import com.maxkeppeker.sheets.core.models.base.rememberUseCaseState
import com.maxkeppeler.sheets.calendar.CalendarDialog
import com.maxkeppeler.sheets.calendar.models.CalendarConfig
import com.maxkeppeler.sheets.calendar.models.CalendarSelection
import com.maxkeppeler.sheets.calendar.models.CalendarStyle
import java.time.LocalDate
import java.util.*


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DueDate(
    modifier: Modifier = Modifier,
    dueDate: String = LocalDate.now().toString(),
    onDueDateChange: (String) -> Unit,
    dueDateMenu: DueDateMenu,
    dueDateMenuExpand: Boolean,
    onExpandClick: () -> Unit,
    onDismissRequest: () -> Unit,
    onMenuItemClick: (DueDateMenu) -> Unit
) {
    val calendarState = rememberUseCaseState()
    CalendarDialog(
        state = calendarState,
        config = CalendarConfig(
            yearSelection = true,
            monthSelection = true,
            style = CalendarStyle.MONTH,
            boundary = dueDateMenu.boundary
        ),
        selection = CalendarSelection.Date(
            selectedDate = LocalDate.parse(dueDate)
        ) { newDate ->

            if (newDate in dueDateMenu.boundary) onDueDateChange(newDate.toString())
            else onDueDateChange(LocalDate.now().toString())
        },
    )

    Column(modifier = modifier) {
        Text(text = "Due Date", style = MaterialTheme.typography.displayMedium, fontSize = 16.sp)
        Text(text = "Select your due date", fontSize = 14.sp)
        ExposedDropdownMenuBox(expanded = dueDateMenuExpand,
            onExpandedChange = {
                onExpandClick()
            }) {
            OutlinedTextField(
                modifier = Modifier
                    .menuAnchor()
                    .fillMaxWidth(),
                value = dueDateMenu.name,
                onValueChange = { }, readOnly = true,
                label = { Text(text = "Based On") },
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(
                        expanded = dueDateMenuExpand
                    )
                },
                colors = ExposedDropdownMenuDefaults.outlinedTextFieldColors(),
            )
            ExposedDropdownMenu(
                expanded = dueDateMenuExpand,
                onDismissRequest = onDismissRequest,
                modifier = Modifier.width(200.dp)
            ) {
                Constants.DUE_DATE_MENU_LIST.forEach { dueDateMenu ->
                    DropdownMenuItem(
                        onClick = {
                            val date = LocalDate.parse(dueDate)
                            if (date in dueDateMenu.boundary) onDueDateChange(date.toString())
                            else onDueDateChange(LocalDate.now().toString())
                            onMenuItemClick(dueDateMenu)
                        },
                        text = { Text(text = dueDateMenu.name) }
                    )
                }
            }
        }
        Spacer(modifier = modifier.padding(4.dp))
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth(),
            value = Utils.TimeFormatter(LocalDate.parse(dueDate)),
            interactionSource = remember { MutableInteractionSource() }
                .also { interactionSource ->
                    LaunchedEffect(interactionSource) {
                        interactionSource.interactions.collect {
                            if (it is PressInteraction.Release) {
                                // works like onClick
                                calendarState.show()
                            }
                        }
                    }
                },
            onValueChange = {

            },
            readOnly = true,
            label = { Text(text = "Date") },
            colors = ExposedDropdownMenuDefaults.outlinedTextFieldColors(),
        )


    }


}


