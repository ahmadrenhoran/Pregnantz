package com.ahmadrenhoran.pregnantz.domain.usecase.tool

import com.ahmadrenhoran.pregnantz.domain.repository.ToolRepository

class InsertPanicNumber(val repository: ToolRepository){

    suspend operator fun invoke(number: String) = repository.insertPanicNumber(number)

}