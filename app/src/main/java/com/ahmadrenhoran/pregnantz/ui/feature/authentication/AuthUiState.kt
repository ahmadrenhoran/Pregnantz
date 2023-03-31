package com.ahmadrenhoran.pregnantz.ui.feature.authentication

data class AuthUiState (
    val email: String = "",
    val password: String = "",
    val passwordVisibility: Boolean = false
)