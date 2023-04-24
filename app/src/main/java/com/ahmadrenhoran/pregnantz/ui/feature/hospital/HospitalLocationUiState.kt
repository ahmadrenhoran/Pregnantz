package com.ahmadrenhoran.pregnantz.ui.feature.hospital

import android.location.Location
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapUiSettings

data class HospitalLocationUiState(
    val uiSettings: MapUiSettings = MapUiSettings(zoomControlsEnabled = false, myLocationButtonEnabled = false),
    val mapProperties: MapProperties = MapProperties(isTrafficEnabled = true, isBuildingEnabled = true, mapStyleOptions = MapStyleOptions(MapStyle.json)),
    val lastKnownLocation: Location? = null,
    val shouldShowPermissionDialog: Boolean = false,
    val isShowDetailPlace: Boolean = false,
)