package com.ahmadrenhoran.pregnantz.core

import com.ahmadrenhoran.pregnantz.domain.model.User
import com.google.firebase.auth.FirebaseUser
import java.util.regex.Pattern

object Utils {
    fun toUser(user: FirebaseUser): User {
        user.apply {
            return User(uid, displayName ?: "", email!!)
        }
    }

    fun isEmailValid(email: String) = android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()

    fun isPasswordValid(password: String): Boolean {

        // minimal
        if (password.length < 8) return false

        // minimal ada satu angka
        var pattern = Pattern.compile("(?=.*[0-9])")
        if (!pattern.matcher(password).find()) return false

        return true
    }
}