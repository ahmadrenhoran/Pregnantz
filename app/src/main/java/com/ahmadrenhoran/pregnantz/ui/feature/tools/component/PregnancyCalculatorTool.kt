package com.ahmadrenhoran.pregnantz.ui.feature.tools.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.ahmadrenhoran.pregnantz.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PregnancyCalculatorTool(modifier: Modifier = Modifier, onClick: () -> Unit) {
    Card(modifier = modifier, onClick = onClick) {
        Column(modifier = Modifier
            .padding(16.dp)) {
            Text(text = "Pregnancy Calculator", modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center)
            Spacer(modifier = Modifier.height(12.dp))

            Image(painter = painterResource(id = R.drawable.calculator_icon), contentDescription = "Pregnancy Calculator", modifier = Modifier.height(180.dp))
        }
    }
}