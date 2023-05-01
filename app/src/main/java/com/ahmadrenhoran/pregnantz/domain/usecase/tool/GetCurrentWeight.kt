package com.ahmadrenhoran.pregnantz.domain.usecase.tool

import com.ahmadrenhoran.pregnantz.domain.repository.ToolRepository

class GetCurrentWeight(private val toolRepository: ToolRepository) {
    suspend operator fun invoke() = toolRepository.getCurrentWeight()
}