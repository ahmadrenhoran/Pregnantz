package com.ahmadrenhoran.pregnantz.data.repository

import android.location.Location
import com.ahmadrenhoran.pregnantz.core.Constants
import com.ahmadrenhoran.pregnantz.core.Utils
import com.ahmadrenhoran.pregnantz.data.remote.GoogleMapApi
import com.ahmadrenhoran.pregnantz.domain.model.Place
import com.ahmadrenhoran.pregnantz.domain.model.Response
import com.ahmadrenhoran.pregnantz.domain.repository.GetDetailPlaceResponse
import com.ahmadrenhoran.pregnantz.domain.repository.GetNearbyHospitalResponse
import com.ahmadrenhoran.pregnantz.domain.repository.HospitalLocationRepository
import com.google.android.gms.maps.model.LatLng
import com.google.android.libraries.places.api.net.FetchPlaceRequest
import com.google.android.libraries.places.api.net.PlacesClient
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class HospitalLocationRepositoryImpl @Inject constructor(private val googleMapApi: GoogleMapApi) : HospitalLocationRepository {
    override suspend fun getDetailPlace(
        placesClient: PlacesClient,
        placeId: String
    ): GetDetailPlaceResponse {
        return try {
            val request = FetchPlaceRequest.newInstance(placeId, Constants.PLACE_FIELDS)
            val response = placesClient.fetchPlace(request).await()
            val place = response.place.let {
                Place(it.id, it.name, it.address, noTelp = it.phoneNumber)
            }
            Response.Success(place)
        } catch (e: Exception) {
            Response.Failure(e)
        }
    }

    override suspend fun getNearbyHospitals(location: Location): GetNearbyHospitalResponse {
        return try {
            val locationStr = Utils.concatLocation(location)
            val response = googleMapApi.getNearbyHospital(location = locationStr)
            if (response.isSuccessful) {
                val body = response.body()!!.results!!
                val hospitals = mutableListOf<Place>()
                body.forEach {
                    if (it != null) {
                        hospitals.add(
                            Place(
                                id = it.placeId,
                                name = it.name,
                                latLng = LatLng(it.geometry.location.lat, it.geometry.location.lng)
                            )
                        )
                    }
                }
                Response.Success(hospitals)
            } else {
                throw Exception("Failed to get nearby hotel")
            }

        } catch (e: Exception) {
            Response.Failure(e)
        }
    }
}

