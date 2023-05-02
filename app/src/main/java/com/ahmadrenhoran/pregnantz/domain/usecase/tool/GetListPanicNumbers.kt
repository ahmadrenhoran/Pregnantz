package com.ahmadrenhoran.pregnantz.domain.usecase.tool

import com.ahmadrenhoran.pregnantz.domain.repository.ToolRepository

class GetListPanicNumbers(val repository: ToolRepository){
    suspend operator fun invoke() = repository.getListPanicNumbers()
}