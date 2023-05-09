package com.ahmadrenhoran.pregnantz.ui.feature.profile

import android.net.Uri
import com.ahmadrenhoran.pregnantz.core.Constants
import com.ahmadrenhoran.pregnantz.core.DueDateMenu
import com.ahmadrenhoran.pregnantz.domain.model.User
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import java.time.LocalDate

data class ProfileUiState(
    val user: User = User(),
    val isAgeMenuExpand: Boolean = false,
    val dueDateMenu: DueDateMenu = Constants.DUE_DATE_MENU_LIST[1],
    val isDueDateMenuExpand: Boolean = false,
)