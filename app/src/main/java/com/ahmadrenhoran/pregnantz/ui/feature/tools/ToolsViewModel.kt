package com.ahmadrenhoran.pregnantz.ui.feature.tools

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class ToolsViewModel @Inject constructor(): ViewModel() {

    private val _uiState = MutableStateFlow(ToolsUiState())
    val uiState: StateFlow<ToolsUiState> = _uiState.asStateFlow()

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