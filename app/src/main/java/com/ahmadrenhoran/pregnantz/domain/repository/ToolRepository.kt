package com.ahmadrenhoran.pregnantz.domain.repository

import com.ahmadrenhoran.pregnantz.domain.model.PanicNumber
import com.ahmadrenhoran.pregnantz.domain.model.Response

interface ToolRepository {

    suspend fun getCurrentWeight(): Response<Float>
    suspend fun insertPanicNumber(number: String): Response<Boolean>
    suspend fun deletePanicNumber(numberId: String): Response<Boolean>
    suspend fun getListPanicNumbers(): Response<List<PanicNumber>>
}