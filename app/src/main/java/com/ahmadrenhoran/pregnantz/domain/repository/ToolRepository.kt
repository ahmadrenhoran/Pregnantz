package com.ahmadrenhoran.pregnantz.domain.repository

import com.ahmadrenhoran.pregnantz.domain.model.Response

interface ToolRepository {

    suspend fun getCurrentWeight(): Response<Float>
}