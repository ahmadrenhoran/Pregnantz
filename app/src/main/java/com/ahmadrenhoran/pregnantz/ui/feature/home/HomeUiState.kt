package com.ahmadrenhoran.pregnantz.ui.feature.home

import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

data class HomeUiState(
    val name: String? = Firebase.auth.currentUser?.displayName,
    val photoUrl: String? = Firebase.auth.currentUser?.photoUrl.toString(),
)