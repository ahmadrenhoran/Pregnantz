package com.ahmadrenhoran.pregnantz.domain.usecase.auth

data class AuthUseCases(
    val signInWithEmail: SignInWithEmail,
    val signUpWithEmail: SignUpWithEmail,
    val addImageToStorage: AddImageToStorage,
    val addDataUserToDatabase: AddDataUserToDatabase,
)