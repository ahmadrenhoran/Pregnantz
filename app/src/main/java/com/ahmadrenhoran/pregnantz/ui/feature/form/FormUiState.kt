package com.ahmadrenhoran.pregnantz.ui.feature.form

import android.net.Uri

data class FormUiState(
    val name: String = "",
    val age: String = "",
    val imageUri: Uri = Uri.parse(""),
    val isAgeMenuExpand: Boolean = false,
)