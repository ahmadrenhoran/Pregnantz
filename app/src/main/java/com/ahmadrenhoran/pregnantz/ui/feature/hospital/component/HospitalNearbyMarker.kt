package com.ahmadrenhoran.pregnantz.ui.feature.hospital.component

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.ahmadrenhoran.pregnantz.core.Utils
import com.ahmadrenhoran.pregnantz.R
import com.ahmadrenhoran.pregnantz.domain.model.Response
import com.ahmadrenhoran.pregnantz.ui.feature.hospital.HospitalLocationViewModel
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerInfoWindow
import com.google.maps.android.compose.rememberMarkerState

@Composable
fun HospitalNearbyMarker(viewModel: HospitalLocationViewModel = hiltViewModel(), onInfoWindowClick: (placeId: String?)-> Unit) {
    when (val getNearbyHospitalResponse = viewModel.getNearbyHospitalResponse) {
        is Response.Loading -> {}
        is Response.Success -> getNearbyHospitalResponse.data.let { places ->
            places.forEach{place ->
                MarkerInfoWindow(
                    state = rememberMarkerState(position = place.latLng!!),
                    title = place.name,
                    snippet = place.address,
                    onInfoWindowClick = {
                        onInfoWindowClick(place.id)
                    },
                )
            }

        }
        is Response.Failure -> getNearbyHospitalResponse.apply {
            var errorDialogPopupShown by remember { mutableStateOf(false) }
            var errorDesc by remember { mutableStateOf("") }

            LaunchedEffect(e) {
                errorDesc = e.localizedMessage
                errorDialogPopupShown = true
            }
            if (errorDialogPopupShown) {
                com.ahmadrenhoran.pregnantz.ui.component.Dialog(
                    text = errorDesc, onConfirm = { errorDialogPopupShown = false }
                ) { errorDialogPopupShown = false }
            }
        }
    }
}

