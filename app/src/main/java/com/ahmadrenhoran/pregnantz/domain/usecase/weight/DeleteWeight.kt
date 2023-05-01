package com.ahmadrenhoran.pregnantz.domain.usecase.weight

import com.ahmadrenhoran.pregnantz.domain.repository.WeightRepository

class DeleteWeight(val repository: WeightRepository) {

    suspend operator fun invoke(weightId: String) = repository.delWeight(weightId = weightId)
}