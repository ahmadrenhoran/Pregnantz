package com.ahmadrenhoran.pregnantz.domain.usecase.profile

import com.ahmadrenhoran.pregnantz.domain.model.User
import com.ahmadrenhoran.pregnantz.domain.repository.ProfileRepository

class UpdateUserData(val repository: ProfileRepository) {
    suspend operator fun invoke(user: User) = repository.updateUserData(user)
}