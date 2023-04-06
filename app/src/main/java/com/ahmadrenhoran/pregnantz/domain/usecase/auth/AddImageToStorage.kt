package com.ahmadrenhoran.pregnantz.domain.usecase.auth

import android.net.Uri
import com.ahmadrenhoran.pregnantz.domain.repository.AuthRepository

class AddImageToStorage(private val repository: AuthRepository) {

    suspend operator fun invoke(imageUri: Uri)  = repository.firebaseAddImageToStorage(imageUri)

}