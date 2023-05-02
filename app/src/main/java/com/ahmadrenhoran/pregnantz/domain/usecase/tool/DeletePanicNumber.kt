package com.ahmadrenhoran.pregnantz.domain.usecase.tool

import com.ahmadrenhoran.pregnantz.domain.repository.ToolRepository

class DeletePanicNumber(val repository: ToolRepository){

    suspend operator fun invoke(numberId: String) = repository.deletePanicNumber(numberId)

}