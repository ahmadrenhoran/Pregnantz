package com.ahmadrenhoran.pregnantz.ui.feature.tools

import com.ahmadrenhoran.pregnantz.domain.usecase.tool.GetCurrentWeight

data class ToolsUiState(
    val currentWeight: Float = 0f,
    val shouldShowPermissionDialog: Boolean = false,
    val shouldShowGPSDialog: Boolean = false,
    val shouldShowWeightDialog: Boolean = false,
)