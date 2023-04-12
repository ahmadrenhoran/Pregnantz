package com.ahmadrenhoran.pregnantz.domain.usecase.auth

import android.net.Uri
import com.ahmadrenhoran.pregnantz.domain.repository.AuthRepository

class AddDataUserToDatabase(private val repository: AuthRepository) {

    suspend operator fun invoke(
        name: String,
        age: String,
        dueDate: String,
        imageUri: Uri
    ) = repository.firebaseAddDataUserToDatabase(name, age, dueDate, imageUri)

}