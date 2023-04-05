package com.ahmadrenhoran.pregnantz.ui.component

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ahmadrenhoran.pregnantz.core.Constants

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuSelectAge(modifier: Modifier = Modifier, age: String, expanded: Boolean, onExpandClick: () -> Unit, onMenuItemClick: (String) -> Unit) {
    OutlinedTextField(
        value = age,
        onValueChange = { }, readOnly = true, enabled = false,
        label = { Text(text = "Age") },
        modifier = modifier
            .fillMaxWidth()
            .clickable(onClick = onExpandClick), colors = TextFieldDefaults.outlinedTextFieldColors(
            disabledTextColor = MaterialTheme.colorScheme.onSurface,
            disabledBorderColor = MaterialTheme.colorScheme.outline,
            disabledPlaceholderColor = MaterialTheme.colorScheme.onSurfaceVariant,
            disabledLabelColor = MaterialTheme.colorScheme.onSurfaceVariant,
        )
    )


    DropdownMenu(
        expanded = expanded,
        onDismissRequest = onExpandClick,
        modifier = Modifier.width(200.dp)
    ) {
        Constants.AGE_LIST.forEach { ageUser ->
            DropdownMenuItem(
                onClick = { onMenuItemClick(ageUser) }, text = { Text(text = ageUser) }
            )
        }
    }
}