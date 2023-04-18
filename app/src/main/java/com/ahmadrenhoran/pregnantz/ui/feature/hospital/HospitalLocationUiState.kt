package com.ahmadrenhoran.pregnantz.ui.feature.hospital

import android.location.Location
import com.google.maps.android.compose.MapUiSettings

data class HospitalLocationUiState(
    val uiSettings: MapUiSettings = MapUiSettings(zoomControlsEnabled = false),
    val lastKnownLocation: Location? = null,
    val shouldShowPermissionDialog: Boolean = false
)