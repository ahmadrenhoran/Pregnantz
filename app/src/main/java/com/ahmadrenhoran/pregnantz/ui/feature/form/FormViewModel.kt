package com.ahmadrenhoran.pregnantz.ui.feature.form

import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import com.ahmadrenhoran.pregnantz.ui.feature.authentication.AuthUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class FormViewModel @Inject constructor(): ViewModel() {
    private val _uiState = MutableStateFlow(FormUiState())
    val uiState: StateFlow<FormUiState> = _uiState.asStateFlow()

    fun setName(name: String) {
        _uiState.update {
            it.copy(name = name)
        }
    }

    fun setAge(age: String) {
        _uiState.update {
            it.copy(age = age)
        }
    }

    fun setAgeMenuExpand(expand: Boolean) {
        _uiState.update {
            it.copy(isAgeMenuExpand = expand)
        }
    }

}