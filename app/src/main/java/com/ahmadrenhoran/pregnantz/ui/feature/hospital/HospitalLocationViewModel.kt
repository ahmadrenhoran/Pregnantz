package com.ahmadrenhoran.pregnantz.ui.feature.hospital

import androidx.lifecycle.ViewModel
import com.ahmadrenhoran.pregnantz.ui.feature.form.FormUiState
import com.google.android.gms.location.FusedLocationProviderClient
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class HospitalLocationViewModel @Inject constructor(): ViewModel() {
    private val _uiState = MutableStateFlow(HospitalLocationUiState())
    val uiState: StateFlow<HospitalLocationUiState> = _uiState.asStateFlow()


    fun getDeviceLocation(
        fusedLocationProviderClient: FusedLocationProviderClient
    ) {
        try {
            val locationResult = fusedLocationProviderClient.lastLocation
            locationResult.addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    _uiState.value = _uiState.value.copy(
                        lastKnownLocation = task.result,
                    )
                }
            }
        } catch (e: SecurityException) {
            // Show error or something
        }
    }

    fun setShouldShowPermissionDialog(boolean: Boolean) {
        _uiState.update {
            it.copy(shouldShowPermissionDialog = boolean)
        }
    }
}