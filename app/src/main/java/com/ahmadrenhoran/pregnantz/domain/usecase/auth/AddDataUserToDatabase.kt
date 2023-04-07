package com.ahmadrenhoran.pregnantz.domain.usecase.auth

import android.net.Uri
import com.ahmadrenhoran.pregnantz.domain.model.User
import com.ahmadrenhoran.pregnantz.domain.repository.AuthRepository

class AddDataUserToDatabase(private val repository: AuthRepository) {

    suspend operator fun invoke(name: String,
                                age: String,
                                imageUri: Uri
    )  = repository.firebaseAddDataUserToDatabase(name, age, imageUri)

}