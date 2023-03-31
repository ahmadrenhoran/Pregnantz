package com.ahmadrenhoran.pregnantz.domain.usecase.auth


import com.ahmadrenhoran.pregnantz.domain.repository.AuthRepository

class SignUpWithEmail(private val repository: AuthRepository) {

    suspend operator fun invoke(email: String, password: String)  = repository.firebaseSignUpWithEmail(email, password)

}