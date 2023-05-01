package com.ahmadrenhoran.pregnantz.domain.repository

import com.ahmadrenhoran.pregnantz.domain.model.Response
import com.ahmadrenhoran.pregnantz.domain.model.Weight


interface WeightRepository {

    suspend fun addWeight(weight: Float): Response<Boolean>
    suspend fun delWeight(weightId: String): Response<Boolean>
    suspend fun getWeightHistory(): Response<List<Weight>>

}