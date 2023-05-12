package com.ahmadrenhoran.pregnantz.ui.feature.home

import com.ahmadrenhoran.pregnantz.domain.model.User

data class HomeUiState(
    val user: User = User(),
)