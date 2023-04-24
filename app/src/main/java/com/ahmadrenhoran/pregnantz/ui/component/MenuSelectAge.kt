package com.ahmadrenhoran.pregnantz.ui.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ahmadrenhoran.pregnantz.core.Constants

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuSelectAge(
    modifier: Modifier = Modifier,
    age: String,
    expanded: Boolean,
    onExpandClick: () -> Unit,
    onDismissRequest: () -> Unit,
    onMenuItemClick: (String) -> Unit
) {

    ExposedDropdownMenuBox(modifier = modifier,
        expanded = expanded,
        onExpandedChange = {
            onExpandClick()
        }) {
        OutlinedTextField(
            modifier = Modifier
                .menuAnchor()
                .fillMaxWidth(),
            value = age,
            onValueChange = { }, readOnly = true,
            label = { Text(text = "Age") },
            colors = ExposedDropdownMenuDefaults.outlinedTextFieldColors(),
        )
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = onDismissRequest,
            modifier = Modifier.width(200.dp)
        ) {
            Constants.AGE_LIST.forEach { ageUser ->
                DropdownMenuItem(
                    onClick = { onMenuItemClick(ageUser) },
                    text = { Text(text = ageUser) }
                )
            }
        }
    }
}