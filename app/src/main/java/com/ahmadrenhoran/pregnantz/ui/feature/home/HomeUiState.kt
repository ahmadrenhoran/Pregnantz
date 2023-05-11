package com.ahmadrenhoran.pregnantz.ui.feature.home

import com.ahmadrenhoran.pregnantz.domain.model.User
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

data class HomeUiState(
    val user: User = User(),
)