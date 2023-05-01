package com.ahmadrenhoran.pregnantz.domain.usecase.weight

import com.ahmadrenhoran.pregnantz.domain.repository.WeightRepository

class GetWeightHistory(private val repository: WeightRepository) {

    suspend operator fun invoke() = repository.getWeightHistory()
}