package com.ahmadrenhoran.pregnantz.ui.feature.tools.component

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ahmadrenhoran.pregnantz.core.Constants
import com.ahmadrenhoran.pregnantz.domain.model.Response
import com.ahmadrenhoran.pregnantz.ui.feature.tools.ToolsViewModel
import com.ahmadrenhoran.pregnantz.ui.feature.weight.component.WeightDialog


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeightTool(
    modifier: Modifier = Modifier,
    viewModel: ToolsViewModel = hiltViewModel(),
    onClick: () -> Unit,
    onClickAddWeight: () -> Unit
) {
    val uiState = viewModel.uiState.collectAsState()
    Card(modifier = modifier, onClick = onClick) {
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            Text(text = "My Weight")
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = if (uiState.value.currentWeight == 0f)
                    "No Data" else " ${uiState.value.currentWeight} KG",
                modifier = Modifier.fillMaxWidth(),
                style = MaterialTheme.typography.displaySmall,
                fontSize = 40.sp,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(12.dp))
            Button(onClick = onClickAddWeight, modifier = Modifier.fillMaxWidth()) {
                Text(text = "Add Weight")
            }
        }
    }

    WeightDialog(
        onClick = {
            viewModel.addWeight()
            viewModel.getCurrentWeight()
            viewModel.setShouldShowWeightDialog(false)
        },
        isShow = uiState.value.shouldShowWeightDialog,
        weightKg = uiState.value.currentWeight.toFloat(),
        onValueChange = {
            try {
                viewModel.setCurrentWeight(it.toFloat())
            } catch (e: NumberFormatException) {

            }
        },
        onDismissRequest = {
            viewModel.setShouldShowWeightDialog(false)
        },
    )

    when (val response = viewModel.getCurrentWeightResponse) {
        is Response.Loading -> {}
        is Response.Success -> response.data.let {
            LaunchedEffect(it) {
                viewModel.setCurrentWeight(it)
            }
        }
        is Response.Failure -> {
//            viewModel.setCurrentWeight(0f)
        }
    }


}