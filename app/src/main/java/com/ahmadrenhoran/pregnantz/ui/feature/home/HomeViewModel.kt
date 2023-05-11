package com.ahmadrenhoran.pregnantz.ui.feature.home

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ahmadrenhoran.pregnantz.core.PregnancyUtils
import com.ahmadrenhoran.pregnantz.domain.model.Response
import com.ahmadrenhoran.pregnantz.domain.model.User
import com.ahmadrenhoran.pregnantz.domain.usecase.home.HomeUseCase
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(val useCase: HomeUseCase): ViewModel() {


    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState = _uiState.asStateFlow()

    val dueDate get() = LocalDate.parse(_uiState.value.user.dueDate)
    val currentWeek get() = PregnancyUtils.getCurrentWeek(dueDate = dueDate)
    val currentDays get() = PregnancyUtils.getCurrentDays(dueDate = dueDate)
    val trimester get() = PregnancyUtils.getTrimester(currentWeek)

    var getUserDataResponse by mutableStateOf<Response<User>>(
        Response.Success(User())
    )
        private set

    init {
        getUserData()
    }

    fun getUserData() = viewModelScope.launch {
        getUserDataResponse = Response.Loading
        getUserDataResponse = useCase.getUserData.invoke()
    }

    fun setUser(user: User) {
        _uiState.update { it.copy(user = user) }
    }

}