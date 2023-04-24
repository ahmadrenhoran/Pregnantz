package com.ahmadrenhoran.pregnantz.domain.repository

import com.ahmadrenhoran.pregnantz.domain.model.Place
import com.ahmadrenhoran.pregnantz.domain.model.Response
import com.google.android.libraries.places.api.net.PlacesClient


typealias GetDetailPlaceResponse = Response<Place>
interface HospitalLocationRepository {
    suspend fun getDetailPlace(placesClient: PlacesClient, placeId: String): GetDetailPlaceResponse

    suspend fun getNearbyHospitals()
}