package com.ahmadrenhoran.pregnantz.ui.feature.weight

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ahmadrenhoran.pregnantz.domain.model.Response
import com.ahmadrenhoran.pregnantz.domain.model.Weight
import com.ahmadrenhoran.pregnantz.domain.usecase.weight.WeightUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeightViewModel @Inject constructor(private val useCase: WeightUseCases): ViewModel() {

    private val _uiState = MutableStateFlow(WeightUiState())
    val uiState: StateFlow<WeightUiState> = _uiState.asStateFlow()

    var addWeightResponse by mutableStateOf<Response<Boolean>>(Response.Success(false))
        private set

    var delWeightResponse by mutableStateOf<Response<Boolean>>(Response.Success(false))
        private set

    var getWeightHistoryResponse by mutableStateOf<Response<List<Weight>>>(Response.Success(listOf()))
        private set

    fun addWeight() = viewModelScope.launch {
        addWeightResponse = Response.Loading
        addWeightResponse = useCase.addWeight.invoke(_uiState.value.weightKg)
    }

    fun delWeight(weightId: String) = viewModelScope.launch {
        addWeightResponse = Response.Loading
        addWeightResponse = useCase.deleteWeight.invoke(weightId)
    }

    fun getWeightHistory() = viewModelScope.launch {
        getWeightHistoryResponse = Response.Loading
        getWeightHistoryResponse = useCase.getWeightHistory.invoke()
    }

    init {
        getWeightHistory()
    }

    fun setWeightKg(weightKg: Float) {
        _uiState.update {
            it.copy(
                weightKg = weightKg
            )
        }
    }


    fun setShowAddWeightDialog(boolean: Boolean) {
        _uiState.update {
            it.copy(
                showAddWeightDialog = boolean
            )
        }
    }

    fun setWeightHistory(weightHistory: List<Weight>) {
        _uiState.update {
            it.copy(
                weightHistory = weightHistory
            )
        }
    }
}