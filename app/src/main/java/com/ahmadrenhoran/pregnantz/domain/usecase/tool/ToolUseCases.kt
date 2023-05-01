package com.ahmadrenhoran.pregnantz.domain.usecase.tool

import com.ahmadrenhoran.pregnantz.domain.usecase.weight.AddWeight

data class ToolUseCases(val getCurrentWeight: GetCurrentWeight, val addWeight: AddWeight)