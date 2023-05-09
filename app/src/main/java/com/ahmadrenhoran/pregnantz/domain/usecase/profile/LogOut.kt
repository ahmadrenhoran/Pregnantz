package com.ahmadrenhoran.pregnantz.domain.usecase.profile

import com.ahmadrenhoran.pregnantz.domain.repository.ProfileRepository

class LogOut(val repository: ProfileRepository) {
    suspend fun invoke() = repository.signOut()
}