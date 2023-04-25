package com.ahmadrenhoran.pregnantz.domain.usecase.hospitallocation

import android.location.Location
import com.ahmadrenhoran.pregnantz.data.repository.HospitalLocationRepositoryImpl
import com.ahmadrenhoran.pregnantz.domain.repository.HospitalLocationRepository
import com.google.android.libraries.places.api.net.PlacesClient

class GetNearbyHospital(val repositoryImpl: HospitalLocationRepository) {

    suspend operator fun invoke(location: Location) =
        repositoryImpl.getNearbyHospitals(location = location)
}