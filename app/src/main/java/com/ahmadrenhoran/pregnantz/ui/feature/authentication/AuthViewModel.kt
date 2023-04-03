package com.ahmadrenhoran.pregnantz.ui.feature.authentication

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ahmadrenhoran.pregnantz.core.Utils
import com.ahmadrenhoran.pregnantz.domain.model.Response
import com.ahmadrenhoran.pregnantz.domain.repository.SignInWithEmailResponse
import com.ahmadrenhoran.pregnantz.domain.repository.SignUpWithEmailResponse
import com.ahmadrenhoran.pregnantz.domain.usecase.auth.AuthUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class AuthViewModel @Inject constructor(private val authUseCases: AuthUseCases) : ViewModel() {

    private val _uiState = MutableStateFlow(AuthUiState())
    val uiState: StateFlow<AuthUiState> = _uiState.asStateFlow()

    var emailResponseSignIn by mutableStateOf<SignInWithEmailResponse>(Response.Success(false))
        private set

    var emailResponseSignUp by mutableStateOf<SignUpWithEmailResponse>(Response.Success(false))
        private set

    var isEmailValid = MutableStateFlow(true)
        private set

    var isPasswordValid = MutableStateFlow(true)
        private set

    fun login() = viewModelScope.launch {
        emailResponseSignIn = Response.Loading
        emailResponseSignIn =
            authUseCases.signInWithEmail(uiState.value.email, uiState.value.password)
    }

    fun register() = viewModelScope.launch {
        validatingEmailAndPassword()
        if (isEmailValid.value && isPasswordValid.value) {
            emailResponseSignUp = Response.Loading
            emailResponseSignUp = authUseCases.signUpWithEmail(uiState.value.email, uiState.value.password)
        }
    }

    fun validatingEmailAndPassword() {
        isEmailValid.value = Utils.isEmailValid(_uiState.value.email)
        isPasswordValid.value = Utils.isPasswordValid(_uiState.value.password)
    }

    fun setEmail(email: String) {
        isEmailValid.value = Utils.isEmailValid(email)
        _uiState.update {
            it.copy(email = email)
        }
    }

    fun setPassword(password: String) {
        isPasswordValid.value = Utils.isPasswordValid(password)
        _uiState.update {
            it.copy(password = password)
        }
    }

    fun setPasswordVisibility(passwordVisibility: Boolean) {
        _uiState.update {
            it.copy(passwordVisibility = passwordVisibility)
        }
    }

}