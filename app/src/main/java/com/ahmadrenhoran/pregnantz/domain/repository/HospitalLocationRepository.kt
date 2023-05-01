package com.ahmadrenhoran.pregnantz.domain.repository

import android.location.Location
import com.ahmadrenhoran.pregnantz.domain.model.Place
import com.ahmadrenhoran.pregnantz.domain.model.Response
import com.google.android.libraries.places.api.net.PlacesClient


typealias GetDetailPlaceResponse = Response<Place>
typealias GetNearbyHospitalResponse = Response<List<Place>>
interface HospitalLocationRepository {
    suspend fun getDetailPlace(placesClient: PlacesClient, placeId: String): GetDetailPlaceResponse

    suspend fun getNearbyHospitals(location: Location): GetNearbyHospitalResponse
}