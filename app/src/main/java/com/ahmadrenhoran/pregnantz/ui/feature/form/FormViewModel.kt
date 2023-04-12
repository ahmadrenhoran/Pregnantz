package com.ahmadrenhoran.pregnantz.ui.feature.form

import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ahmadrenhoran.pregnantz.core.Constants
import com.ahmadrenhoran.pregnantz.core.DueDateMenu
import com.ahmadrenhoran.pregnantz.core.Utils
import com.ahmadrenhoran.pregnantz.domain.model.Response
import com.ahmadrenhoran.pregnantz.domain.repository.AddDataUserToDatabaseResponse
import com.ahmadrenhoran.pregnantz.domain.repository.AddImageToStorageResponse
import com.ahmadrenhoran.pregnantz.domain.usecase.auth.AuthUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class FormViewModel @Inject constructor(private val authUseCases: AuthUseCases) : ViewModel() {
    private val _uiState = MutableStateFlow(FormUiState())
    val uiState: StateFlow<FormUiState> = _uiState.asStateFlow()

    var addImageToStorageResponse by mutableStateOf<AddImageToStorageResponse>(
        Response.Success(
            Uri.parse(
                ""
            )
        )
    )
        private set

    var addDataUserToDatabaseResponse by mutableStateOf<AddDataUserToDatabaseResponse>(
        Response.Success(
            false
        )
    )
        private set

    fun addImageToStorage(imageUri: Uri) = viewModelScope.launch {
        addImageToStorageResponse = Response.Loading
        addImageToStorageResponse = authUseCases.addImageToStorage.invoke(imageUri)
    }

    fun addDataUserToDatabase() = viewModelScope.launch {
        _uiState.value.apply {
            if (name != null && age != null && date != null) {
                addDataUserToDatabaseResponse = Response.Loading
                addDataUserToDatabaseResponse =
                    authUseCases.addDataUserToDatabase.invoke(name, age, getDueDate(), imageUri)

            }
        }

    }

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

    fun setDate(date: String) {
        _uiState.update {
            it.copy(date = date)
        }
    }

    fun setImageUri(imageUri: Uri) {
        _uiState.update {
            it.copy(imageUri = imageUri)
        }
    }

    fun setAgeMenuExpand(expand: Boolean) {
        _uiState.update {
            it.copy(isAgeMenuExpand = expand)
        }
    }

    fun setDueDateMenu(dueDateMenu: DueDateMenu) {
        _uiState.update {
            it.copy(dueDateMenu = dueDateMenu)
        }
    }

    fun setDueDateMenuExpand(isDueDateMenuExpand: Boolean) {
        _uiState.update {
            it.copy(isDueDateMenuExpand = isDueDateMenuExpand)
        }
    }

    fun getDueDate(): String {
        if (_uiState.value.dueDateMenu.name == Constants.FIRST_DAY_OF_LAST_PERIOD) {
            return Utils.getFirstDayOfLastPeriodDueDate(LocalDate.parse(_uiState.value.date))
        } else { // Estimated Due Date
            return _uiState.value.date
        }
    }

    // get remaining weeks
    fun getDueWeek() = Utils.getDueWeeks(LocalDate.parse(getDueDate()))

    // get remaining days
    fun getDueDay() = Utils.getDueDays(LocalDate.parse(getDueDate()))
}