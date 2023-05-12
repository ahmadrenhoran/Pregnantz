package com.ahmadrenhoran.pregnantz.ui.feature.profile

import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ahmadrenhoran.pregnantz.core.Constants
import com.ahmadrenhoran.pregnantz.core.DueDateMenu
import com.ahmadrenhoran.pregnantz.core.Utils
import com.ahmadrenhoran.pregnantz.domain.model.Response
import com.ahmadrenhoran.pregnantz.domain.model.User
import com.ahmadrenhoran.pregnantz.domain.repository.AddImageToStorageResponse
import com.ahmadrenhoran.pregnantz.domain.usecase.profile.ProfileUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject


@HiltViewModel
class ProfileViewModel @Inject constructor(private val useCase: ProfileUseCase) : ViewModel() {

    private val _uiState = MutableStateFlow(ProfileUiState())
    val uiState = _uiState.asStateFlow()

    var getUserDataResponse by mutableStateOf<Response<User>>(
        Response.Success(User())
    )
        private set

    var addDataUserToDbResponse by mutableStateOf<Response<Boolean>>(
        Response.Success(false)
    )
        private set

    var signOutResponse by mutableStateOf<Response<Boolean>>(
        Response.Success(false)
    )
        private set

    var addImageToStorageResponse by mutableStateOf<AddImageToStorageResponse>(
        Response.Success(
            Uri.parse(
                ""
            )
        )
    )
        private set

    init {
        getUserData()
    }

    fun logOut() = viewModelScope.launch { useCase.logOut.invoke() }

    fun getUserData() = viewModelScope.launch {
        getUserDataResponse = Response.Loading
        getUserDataResponse = useCase.getUserData.invoke()
    }

    fun addDataUserToDb() = viewModelScope.launch {
        addDataUserToDbResponse = Response.Loading
        val user = _uiState.value.user
        addDataUserToDbResponse = useCase.updateUserData.invoke(user.copy(dueDate = getDueDate()))
    }

    fun addImageToStorage(imageUri: Uri) = viewModelScope.launch {
        addImageToStorageResponse = Response.Loading
        addImageToStorageResponse = useCase.addImageToStorage.invoke(imageUri)
    }

    fun setUser(user: User) {
        _uiState.update { it.copy(user = user) }
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
            return Utils.getFirstDayOfLastPeriodDueDate(LocalDate.parse(_uiState.value.user.dueDate))
        } else { // Estimated Due Date
            return _uiState.value.user.dueDate
        }
    }
}