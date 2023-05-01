package com.ahmadrenhoran.pregnantz.domain.usecase.weight

import com.ahmadrenhoran.pregnantz.domain.repository.WeightRepository

class AddWeight(val repository: WeightRepository) {

    suspend operator fun invoke(weight: Float) = repository.addWeight(weight = weight)
}