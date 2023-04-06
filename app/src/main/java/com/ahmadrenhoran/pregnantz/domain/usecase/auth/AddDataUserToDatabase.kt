package com.ahmadrenhoran.pregnantz.domain.usecase.auth

import com.ahmadrenhoran.pregnantz.domain.model.User
import com.ahmadrenhoran.pregnantz.domain.repository.AuthRepository

class AddDataUserToDatabase(private val repository: AuthRepository) {

    suspend operator fun invoke(user: User)  = repository.firebaseAddDataUserToDatabase(user)

}