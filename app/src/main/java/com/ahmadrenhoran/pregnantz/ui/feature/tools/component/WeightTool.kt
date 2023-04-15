package com.ahmadrenhoran.pregnantz.ui.feature.tools.component

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeightTool(modifier: Modifier = Modifier, onClick: () -> Unit, onClickAddWeight: () -> Unit) {
    Card(modifier = modifier, onClick = onClick) {
        Column(modifier = Modifier
            .padding(16.dp)) {
            Text(text = "My Weight")
            Spacer(modifier = Modifier.height(12.dp))
            Text(text = "60 KG", modifier = Modifier.fillMaxWidth(), style = MaterialTheme.typography.displaySmall, fontSize = 40.sp, textAlign = TextAlign.Center)

            Spacer(modifier = Modifier.height(12.dp))
            Button(onClick = onClickAddWeight, modifier = Modifier.fillMaxWidth()) {
                Text(text = "Add Weight")
            }
        }
    }
}