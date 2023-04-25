package com.ahmadrenhoran.pregnantz.ui.feature.hospital


import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.LocationSearching
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.ahmadrenhoran.pregnantz.R
import com.ahmadrenhoran.pregnantz.core.Constants
import com.ahmadrenhoran.pregnantz.domain.model.Response
import com.ahmadrenhoran.pregnantz.ui.feature.hospital.component.HospitalDetailPlaceResponse
import com.ahmadrenhoran.pregnantz.ui.feature.hospital.component.HospitalNearbyMarker
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.android.libraries.places.api.Places
import com.google.maps.android.compose.*
import com.google.maps.android.ktx.model.markerOptions


@Composable
fun HospitalLocationScreen(
    modifier: Modifier = Modifier,
    viewModel: HospitalLocationViewModel = hiltViewModel(), context: Context,
) {
    val fusedLocationProviderClient =
        LocationServices.getFusedLocationProviderClient(context)
//    viewModel.getDeviceLocation(fusedLocationProviderClient)
    val uiState = viewModel.uiState.collectAsState()
    val placesClient = Places.createClient(LocalContext.current)

    val cameraPositionState = rememberCameraPositionState {
        uiState.value.lastKnownLocation?.apply {
            position = CameraPosition.fromLatLngZoom(
                LatLng(
                    latitude,
                    longitude
                ), 26f
            )

        }
    }

    LaunchedEffect(uiState.value.lastKnownLocation) {
        uiState.value.lastKnownLocation?.apply {
            cameraPositionState.animate(
                update = CameraUpdateFactory.newLatLngZoom(
                    LatLng(latitude, longitude),
                    15f
                )
            )
        }

    }
    val mapProperties by remember {
        mutableStateOf(
            MapProperties(
                mapStyleOptions = MapStyleOptions.loadRawResourceStyle(context, R.raw.map_style),
                isMyLocationEnabled = true,
                isTrafficEnabled = true
            )
        )
    }


    Scaffold(
        modifier = modifier, floatingActionButtonPosition = FabPosition.Center,
        floatingActionButton = {
            FloatingActionButton(onClick = {
                viewModel.getDeviceLocation(fusedLocationProviderClient)
            }
            ) {
                Icon(imageVector = Icons.Outlined.LocationSearching, contentDescription = "")
            }
        },
    ) { innerPadding ->
        GoogleMap(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            uiSettings = uiState.value.uiSettings,
            properties = mapProperties,
            cameraPositionState = cameraPositionState,
            onPOIClick = {
                viewModel.getDetailPlace(placesClient, placeId = it.placeId)
//                viewModel.setIsShowDetailPlace(!uiState.value.isShowDetailPlace)
            }, onMyLocationClick = {

            }
        ) {
            HospitalNearbyMarker() { placeId ->

                viewModel.getDetailPlace(placesClient, placeId = placeId!!)
            }


        }

        HospitalDetailPlaceResponse(isShow = uiState.value.isShowDetailPlace, context = context) {
            viewModel.setIsShowDetailPlace(!uiState.value.isShowDetailPlace)
        }

//    if (isSystemInDarkTheme()) {
//        viewModel.setMapProperties(MapProperties())
//    }
    }
}




