package com.ahmadrenhoran.pregnantz.ui.feature.profile

import com.ahmadrenhoran.pregnantz.core.Constants
import com.ahmadrenhoran.pregnantz.core.DueDateMenu
import com.ahmadrenhoran.pregnantz.domain.model.User

data class ProfileUiState(
    val user: User = User(),
    val isAgeMenuExpand: Boolean = false,
    val dueDateMenu: DueDateMenu = Constants.DUE_DATE_MENU_LIST[1],
    val isDueDateMenuExpand: Boolean = false,
)