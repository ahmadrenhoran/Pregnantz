package com.ahmadrenhoran.pregnantz.domain.model

import java.time.LocalDate

data class User(
    val uid: String = "",
    val name: String = "",
    val email: String = "",
    val age: String = "",
    val photoUrl: String = "",
    val dueDate: String = LocalDate.now().toString()
)