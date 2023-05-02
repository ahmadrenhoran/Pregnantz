package com.ahmadrenhoran.pregnantz.ui.feature.tools

import com.ahmadrenhoran.pregnantz.domain.model.PanicNumber

data class ToolsUiState(
    val currentWeight: Float = 0f,
    val panicNumber: String = "",
    val listPanicNumbers: List<PanicNumber> = listOf(),
    val shouldShowPermissionDialog: Boolean = false,
    val shouldShowGPSDialog: Boolean = false,
    val shouldShowWeightDialog: Boolean = false,
    val shouldShowPanicDialog: Boolean = false,
)