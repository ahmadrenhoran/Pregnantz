package com.ahmadrenhoran.pregnantz.ui.feature.form

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.ahmadrenhoran.pregnantz.domain.model.Response
import com.ahmadrenhoran.pregnantz.domain.repository.AddDataUserToDatabaseResponse
import com.ahmadrenhoran.pregnantz.domain.repository.AddImageToStorageResponse
import com.ahmadrenhoran.pregnantz.domain.usecase.auth.AddDataUserToDatabase
import com.ahmadrenhoran.pregnantz.domain.usecase.auth.AuthUseCases
import com.ahmadrenhoran.pregnantz.ui.feature.authentication.AuthUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class FormViewModel @Inject constructor(private val authUseCases: AuthUseCases): ViewModel() {
    private val _uiState = MutableStateFlow(FormUiState())
    val uiState: StateFlow<FormUiState> = _uiState.asStateFlow()

    var addImageToStorageResponse by mutableStateOf<AddImageToStorageResponse>(Response.Success(null))
        private set

    var emailResponseSignUp by mutableStateOf<AddDataUserToDatabaseResponse>(Response.Success(false))
        private set

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