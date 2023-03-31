package com.ahmadrenhoran.pregnantz.domain.usecase.auth

import com.ahmadrenhoran.pregnantz.domain.repository.AuthRepository


class SignInWithEmail(private val repository: AuthRepository) {

    suspend operator fun invoke(email: String, password: String)  = repository.firebaseSignInWithEmail(email, password)

}