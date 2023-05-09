package com.ahmadrenhoran.pregnantz.ui.feature.home

import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(val auth: FirebaseAuth): ViewModel() {

    val displayName get() = auth.currentUser?.displayName.toString()
    val photoUrl get() = auth.currentUser?.photoUrl.toString()

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState = _uiState.asStateFlow()
}