package com.ahmadrenhoran.pregnantz.ui.feature.weight

import com.ahmadrenhoran.pregnantz.domain.model.Weight

data class WeightUiState(
    val weightKg: Float = 1f,
    val showAddWeightDialog: Boolean = false,
    val weightHistory: List<Weight> = listOf()
)
