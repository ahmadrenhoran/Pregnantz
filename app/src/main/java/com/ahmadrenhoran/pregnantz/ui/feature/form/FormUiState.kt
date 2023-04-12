package com.ahmadrenhoran.pregnantz.ui.feature.form

import android.net.Uri
import com.ahmadrenhoran.pregnantz.core.Constants
import com.ahmadrenhoran.pregnantz.core.DueDateMenu
import java.time.LocalDate

data class FormUiState(
    val name: String = "",
    val age: String = "",
    val dueDate: String = LocalDate.now().toString(),
    val date: String = LocalDate.now().toString(),
    val imageUri: Uri = Uri.parse(""),
    val isAgeMenuExpand: Boolean = false,
    val dueDateMenu: DueDateMenu = Constants.DUE_DATE_MENU_LIST[0],
    val isDueDateMenuExpand: Boolean = false,
)