package com.ahmadrenhoran.pregnantz.ui.feature.tools

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ahmadrenhoran.pregnantz.core.Constants
import com.ahmadrenhoran.pregnantz.domain.model.Place
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

    init {
        getCurrentWeight()
    }

    fun getCurrentWeight() = viewModelScope.launch {
        getCurrentWeightResponse = Response.Loading
        getCurrentWeightResponse = useCases.getCurrentWeight.invoke()
    }


    fun addWeight() = viewModelScope.launch {
        useCases.addWeight.invoke(_uiState.value.currentWeight)
    }


    fun setCurrentWeight(float: Float) {
        _uiState.update {
            it.copy(currentWeight = float)
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


}