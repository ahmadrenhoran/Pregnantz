package com.ahmadrenhoran.pregnantz.domain.usecase.profile

import com.ahmadrenhoran.pregnantz.domain.repository.ProfileRepository

class GetUserData(val repository: ProfileRepository) {
    suspend operator fun invoke() = repository.getUserData()
}