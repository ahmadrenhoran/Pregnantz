package com.ahmadrenhoran.pregnantz.ui.feature.hospital

import android.location.Location
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ahmadrenhoran.pregnantz.core.Constants
import com.ahmadrenhoran.pregnantz.domain.model.Place
import com.ahmadrenhoran.pregnantz.domain.model.Response
import com.ahmadrenhoran.pregnantz.domain.repository.GetDetailPlaceResponse
import com.ahmadrenhoran.pregnantz.domain.repository.GetNearbyHospitalResponse
import com.ahmadrenhoran.pregnantz.domain.usecase.hospitallocation.HospitalLocationUseCase
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.libraries.places.api.net.PlacesClient
import com.google.maps.android.compose.MapProperties
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HospitalLocationViewModel @Inject constructor(private val useCase: HospitalLocationUseCase) :
    ViewModel() {
    private val _uiState = MutableStateFlow(HospitalLocationUiState())
    val uiState: StateFlow<HospitalLocationUiState> = _uiState.asStateFlow()

    var getDetailPlaceResponse by mutableStateOf<GetDetailPlaceResponse>(Response.Success(Place()))
        private set

    var getNearbyHospitalResponse by mutableStateOf<GetNearbyHospitalResponse>(
        Response.Success(
            listOf()
        )
    )
        private set

    fun getDetailPlace(placesClient: PlacesClient, placeId: String) = viewModelScope.launch {
        getDetailPlaceResponse = Response.Loading
        getDetailPlaceResponse = useCase.getDetailPlace.invoke(placesClient, placeId)
        setIsShowDetailPlace(!_uiState.value.isShowDetailPlace)
    }

    fun getNearbyHospital() = viewModelScope.launch {
        getNearbyHospitalResponse = Response.Loading
        if (_uiState.value.lastKnownLocation != null) {
            getNearbyHospitalResponse = useCase.getNearbyHospital.invoke(_uiState.value.lastKnownLocation!!)
        } else {
            getNearbyHospitalResponse = Response.Failure(Exception("Can't find location"))
        }
    }


    fun getDeviceLocation(
        fusedLocationProviderClient: FusedLocationProviderClient
    ): Boolean {
        try {
            val locationResult = fusedLocationProviderClient.lastLocation
            locationResult.addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d(Constants.ALL_TAG, "getDeviceLocation: ${task.result}")
                    if (task.result != null) {
                        setLastKnownLocation(task.result)
                        getNearbyHospital()

                    }
//                    setMapProperties(MapProperties(isMyLocationEnabled = task.result != null))
                }
            }
            return true
        } catch (e: SecurityException) {
            // Show error or something
            return false
        }
        return false

    }


    fun setLastKnownLocation(location: Location) {
        _uiState.update {
            it.copy(
                lastKnownLocation = location
            )
        }
    }

    fun setMapProperties(mapProperties: MapProperties) {
        _uiState.update {
            it.copy(
                mapProperties = mapProperties
            )
        }
    }

    fun setIsShowDetailPlace(boolean: Boolean) {
        _uiState.update {
            it.copy(
                isShowDetailPlace = boolean
            )
        }
    }


}