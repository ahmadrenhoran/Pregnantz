package com.ahmadrenhoran.pregnantz.ui.feature.tools

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ahmadrenhoran.pregnantz.ui.article.component.GetArticlesResponse
import com.ahmadrenhoran.pregnantz.ui.feature.tools.component.HospitalTool
import com.ahmadrenhoran.pregnantz.ui.feature.tools.component.PregnancyCalculatorTool
import com.ahmadrenhoran.pregnantz.ui.feature.tools.component.WeightTool

@Composable
fun ToolsScreen(modifier: Modifier = Modifier) {
    Column(modifier = modifier.padding(16.dp)) {
        Text(text = "Tools", fontSize = 24.sp, color = MaterialTheme.colorScheme.onBackground)
        Spacer(modifier = modifier.height(8.dp))
        WeightTool(onClick = {

        }, onClickAddWeight = {

        })
        Spacer(modifier = modifier.height(8.dp))
        Row() {
            HospitalTool(modifier = Modifier
                .weight(1f)
                .height(240.dp)) {

            }
            Spacer(modifier = Modifier.width(8.dp))
            PregnancyCalculatorTool(modifier = Modifier
                .weight(1f)
                .height(240.dp)) {

            }

        }
    }
}