package com.ahmadrenhoran.pregnantz.ui.feature.tools

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ahmadrenhoran.pregnantz.domain.model.PanicNumber
import com.ahmadrenhoran.pregnantz.domain.model.Response
import com.ahmadrenhoran.pregnantz.domain.usecase.tool.ToolUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ToolsViewModel @Inject constructor(private val useCases: ToolUseCases) : ViewModel() {

    private val _uiState = MutableStateFlow(ToolsUiState())
    val uiState: StateFlow<ToolsUiState> = _uiState.asStateFlow()

    var getCurrentWeightResponse by mutableStateOf<Response<Float>>(Response.Success(0f))
        private set

    var insertPanicNumberResponse by mutableStateOf<Response<Boolean>>(Response.Success(false))
        private set

    var deletePanicNumberResponse by mutableStateOf<Response<Boolean>>(Response.Success(false))
        private set

    var getListPanicNumbersResponse by mutableStateOf<Response<List<PanicNumber>>>(Response.Success(listOf()))
        private set

    init {
        getCurrentWeight()
        getListPanicNumbers()
    }

    fun getCurrentWeight() = viewModelScope.launch {
        getCurrentWeightResponse = Response.Loading
        getCurrentWeightResponse = useCases.getCurrentWeight.invoke()
    }

    fun insertPanicNumber() = viewModelScope.launch {
        insertPanicNumberResponse = Response.Loading
        insertPanicNumberResponse = useCases.insertPanicNumber(_uiState.value.panicNumber)
    }

    fun deletePanicNumber(numberId: String) = viewModelScope.launch {
        deletePanicNumberResponse = Response.Loading
        deletePanicNumberResponse = useCases.deletePanicNumber(numberId)
    }

    fun getListPanicNumbers() = viewModelScope.launch {
        getListPanicNumbersResponse = Response.Loading
        getListPanicNumbersResponse = useCases.getListPanicNumbers()
    }


    fun addWeight() = viewModelScope.launch {
        useCases.addWeight.invoke(_uiState.value.currentWeight)
    }


    fun setCurrentWeight(float: Float) {
        _uiState.update {
            it.copy(currentWeight = float)
        }
    }

    fun setPanicNumber(string: String) {
        _uiState.update {
            it.copy(panicNumber = string)
        }
    }

    fun setListPanicNumbers(strings: List<PanicNumber>) {
        _uiState.update {
            it.copy(listPanicNumbers = strings)
        }
    }


    fun setShouldShowPermissionDialog(boolean: Boolean) {
        _uiState.update {
            it.copy(shouldShowPermissionDialog = boolean)
        }
    }

    fun setShouldShowGPSDialog(boolean: Boolean) {
        _uiState.update {
            it.copy(shouldShowGPSDialog = boolean)
        }
    }

    fun setShouldShowWeightDialog(boolean: Boolean) {
        _uiState.update {
            it.copy(shouldShowWeightDialog = boolean)
        }
    }

    fun setShouldShowPanicDialog(boolean: Boolean) {
        _uiState.update {
            it.copy(shouldShowPanicDialog = boolean)
        }
    }


}