package com.ahmadrenhoran.pregnantz.domain.usecase.hospitallocation

import android.location.Location
import com.ahmadrenhoran.pregnantz.domain.repository.HospitalLocationRepository

class GetNearbyHospital(val repositoryImpl: HospitalLocationRepository) {

    suspend operator fun invoke(location: Location) =
        repositoryImpl.getNearbyHospitals(location = location)
}