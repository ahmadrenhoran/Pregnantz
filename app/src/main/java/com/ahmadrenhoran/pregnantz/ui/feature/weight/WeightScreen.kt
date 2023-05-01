package com.ahmadrenhoran.pregnantz.ui.feature.weight

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ahmadrenhoran.pregnantz.core.Constants
import com.ahmadrenhoran.pregnantz.core.Utils
import com.ahmadrenhoran.pregnantz.domain.model.Response
import com.ahmadrenhoran.pregnantz.domain.model.Weight
import com.ahmadrenhoran.pregnantz.ui.component.Dialog
import com.ahmadrenhoran.pregnantz.ui.component.ProgressBar
import com.ahmadrenhoran.pregnantz.ui.feature.weight.component.WeightDialog
import com.ahmadrenhoran.pregnantz.ui.feature.weight.component.rememberChartStyle
import com.ahmadrenhoran.pregnantz.ui.feature.weight.component.rememberMarker
import com.github.tehras.charts.line.LineChart
import com.github.tehras.charts.line.LineChartData

import com.github.tehras.charts.line.renderer.xaxis.SimpleXAxisDrawer
import com.github.tehras.charts.line.renderer.yaxis.SimpleYAxisDrawer
import com.github.tehras.charts.line.renderer.line.SolidLineDrawer
import com.github.tehras.charts.line.renderer.point.HollowCircularPointDrawer
import com.github.tehras.charts.piechart.animation.simpleChartAnimation
import com.patrykandpatrick.vico.compose.axis.horizontal.bottomAxis
import com.patrykandpatrick.vico.compose.axis.vertical.startAxis
import com.patrykandpatrick.vico.compose.chart.Chart
import com.patrykandpatrick.vico.compose.chart.line.lineChart
import com.patrykandpatrick.vico.compose.style.ProvideChartStyle
import com.patrykandpatrick.vico.core.axis.AxisPosition
import com.patrykandpatrick.vico.core.axis.formatter.AxisValueFormatter
import com.patrykandpatrick.vico.core.entry.ChartEntryModelProducer
import com.patrykandpatrick.vico.core.entry.FloatEntry
import com.patrykandpatrick.vico.core.entry.entryModelOf


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnrememberedMutableState")
@Composable
fun WeightScreen(modifier: Modifier = Modifier, viewModel: WeightViewModel = hiltViewModel()) {
    val uiState = viewModel.uiState.collectAsState()
    var horizontalOffset by remember {
        mutableStateOf(5f)
    }
    var horizontalOffset2 by mutableStateOf(5f)

    var lineChartData by mutableStateOf(
        uiState.value.weightHistory.toLineChartData()
    )

    val weightChartList: List<Float> = uiState.value.weightHistory.map { it.weightKg }
    val weightChartList2: List<FloatEntry> =
        List(uiState.value.weightHistory.size) { FloatEntry(it.toFloat(), weightChartList.get(it)) }
    val chartEntryModelProducer = ChartEntryModelProducer(weightChartList2)
    val marker = rememberMarker()




    Scaffold(modifier = modifier.padding(12.dp), floatingActionButton = {
        FloatingActionButton(onClick = { viewModel.setShowAddWeightDialog(true) }) {
            Icon(imageVector = Icons.Outlined.Add, contentDescription = "Add Weight")
        }
    }) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            Text(text = "Weight (kg)", style = MaterialTheme.typography.displayMedium)
            Spacer(modifier = Modifier.height(12.dp))
            Card(shape = MaterialTheme.shapes.large, colors = CardDefaults.elevatedCardColors()) {
                Column(Modifier.padding(12.dp)) {
                    Text(text = "")
                    ProvideChartStyle(rememberChartStyle(chartColors)) {
                        Chart(
                            chart = lineChart(),
                            chartModelProducer = chartEntryModelProducer,
                            startAxis = startAxis(),
                            bottomAxis = bottomAxis(guideline = null),
                            marker = marker
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(12.dp))
            Text(text = "History", style = MaterialTheme.typography.displayMedium)

            Spacer(modifier = Modifier.height(12.dp))
            LazyColumn() {
                items(uiState.value.weightHistory.reversed()) {
                    Card(modifier = Modifier.fillMaxWidth()) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column(modifier = Modifier.padding(12.dp)) {
                                Text(text = "${it.weightKg} kg", fontSize = 16.sp)
                                Text(text = "${Utils.getTimeDate(it.createdAt)}", fontSize = 12.sp)
                            }

                            Button(
                                modifier = Modifier.padding(end = 6.dp),
                                onClick = { viewModel.delWeight(it.id)
                                          viewModel.getWeightHistory()},
                            ) {
                                Icon(
                                    imageVector =
                                    Icons.Outlined.Close,
                                    contentDescription = "Delete",
                                )
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(6.dp))
                }
            }
        }
    }

    WeightDialog(
        onClick = {
            viewModel.addWeight()
            viewModel.getWeightHistory()
            viewModel.setShowAddWeightDialog(false)
        },
        isShow = uiState.value.showAddWeightDialog,
        weightKg = uiState.value.weightKg,
        onValueChange = { viewModel.setWeightKg(it.toFloat()) },
        onDismissRequest = {
            viewModel.setShowAddWeightDialog(false)
        },
    )

    when (val weightHistoryResponse = viewModel.getWeightHistoryResponse) {
        is Response.Loading -> {}
        is Response.Success -> weightHistoryResponse.data.let { weight ->
            LaunchedEffect(weight) {
                viewModel.setWeightHistory(weight)
            }
        }
        is Response.Failure -> weightHistoryResponse.apply {
            var errorDialogPopupShown by remember { mutableStateOf(false) }
            var errorDesc by remember { mutableStateOf("") }
            LaunchedEffect(e) {
                errorDesc = e.localizedMessage
                errorDialogPopupShown = true
            }
            if (errorDialogPopupShown) {
                Dialog(
                    text = errorDesc, onConfirm = { errorDialogPopupShown = false }
                ) { errorDialogPopupShown = false }
            }
        }
    }

}

fun List<Weight>.toLineChartData(): LineChartData {
    val list = mutableListOf<LineChartData.Point>()
    this.forEach { weight ->
        list.add(
            LineChartData.Point(
                value = weight.weightKg,
                label = Utils.getTimeDate(weight.createdAt).toString()
            )
        )
    }
    return LineChartData(
        points = list.toList(), lineDrawer = SolidLineDrawer()
    )
}

private const val COLOR_1_CODE = 0xffa485e0
private const val PERSISTENT_MARKER_X = 10f

private val color1 = Color(COLOR_1_CODE)
private val chartColors = listOf(color1)