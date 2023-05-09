package com.ahmadrenhoran.pregnantz.domain.repository

import com.ahmadrenhoran.pregnantz.domain.model.Response
import com.ahmadrenhoran.pregnantz.domain.model.User

interface ProfileRepository {

    suspend fun signOut() : Response<Boolean>
    suspend fun getUserData() : Response<User>
    suspend fun updateUserData(user: User) : Response<Boolean>
}