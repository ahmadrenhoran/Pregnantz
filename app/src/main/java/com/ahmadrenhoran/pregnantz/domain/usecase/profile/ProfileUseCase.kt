package com.ahmadrenhoran.pregnantz.domain.usecase.profile

import com.ahmadrenhoran.pregnantz.domain.usecase.auth.AddDataUserToDatabase
import com.ahmadrenhoran.pregnantz.domain.usecase.auth.AddImageToStorage

data class ProfileUseCase(val logOut: LogOut, val getUserData: GetUserData, val updateUserData: UpdateUserData, val addImageToStorage: AddImageToStorage)