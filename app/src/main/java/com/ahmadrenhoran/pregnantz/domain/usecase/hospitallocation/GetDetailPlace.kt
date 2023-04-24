package com.ahmadrenhoran.pregnantz.domain.usecase.hospitallocation

import com.ahmadrenhoran.pregnantz.domain.repository.HospitalLocationRepository
import com.google.android.libraries.places.api.net.PlacesClient

class GetDetailPlace(val repository: HospitalLocationRepository) {

    suspend operator fun invoke(placesClient: PlacesClient, placeId: String) =
        repository.getDetailPlace(placesClient, placeId)
}