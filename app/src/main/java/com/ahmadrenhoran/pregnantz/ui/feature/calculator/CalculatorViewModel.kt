package com.ahmadrenhoran.pregnantz.ui.feature.calculator

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import com.ahmadrenhoran.pregnantz.core.Constants
import com.ahmadrenhoran.pregnantz.core.DueDateMenu
import com.ahmadrenhoran.pregnantz.core.PregnancyUtils
import com.ahmadrenhoran.pregnantz.core.Utils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class CalculatorViewModel @Inject constructor() : ViewModel() {
    private val _uiState = MutableStateFlow(CalculatorUiState())
    val uiState: StateFlow<CalculatorUiState> = _uiState.asStateFlow()


    fun setDate(date: String) {
        _uiState.update {
            it.copy(date = date)
        }

    }

    fun setWeekList(weekList: List<LocalDate>) {
        _uiState.update {
            it.copy(weekList = weekList)
        }
    }

    fun setDueDateMenuExpand(boolean: Boolean) {
        _uiState.update {
            it.copy(isDueDateMenuExpand = boolean)
        }
    }

    fun setDueDateMenu(dueDateMenu: DueDateMenu) {
        _uiState.update {
            it.copy(dueDateMenu = dueDateMenu)
        }
    }
}