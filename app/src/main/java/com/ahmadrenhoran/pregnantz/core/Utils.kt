package com.ahmadrenhoran.pregnantz.core

import com.ahmadrenhoran.pregnantz.domain.model.User
import com.google.firebase.auth.FirebaseUser

object Utils {
    fun toUser(user: FirebaseUser): User {
        user.apply {
            return User(uid, displayName ?: "", email!!)
        }
    }
}