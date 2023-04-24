package com.ahmadrenhoran.pregnantz.data.repository

import com.ahmadrenhoran.pregnantz.core.Constants
import com.ahmadrenhoran.pregnantz.domain.model.Place
import com.ahmadrenhoran.pregnantz.domain.model.Response
import com.ahmadrenhoran.pregnantz.domain.repository.GetDetailPlaceResponse
import com.ahmadrenhoran.pregnantz.domain.repository.HospitalLocationRepository
import com.google.android.libraries.places.api.net.FetchPlaceRequest
import com.google.android.libraries.places.api.net.PlacesClient
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class HospitalLocationRepositoryImpl @Inject constructor() : HospitalLocationRepository {
    override suspend fun getDetailPlace(
        placesClient: PlacesClient,
        placeId: String
    ): GetDetailPlaceResponse {
        return try {
            val request = FetchPlaceRequest.newInstance(placeId, Constants.PLACE_FIELDS)
            val response = placesClient.fetchPlace(request).await()
            val place = response.place.let {
                Place(it.name, it.address)
            }
            Response.Success(place)
        } catch (e: Exception) {
            Response.Failure(e)
        }
    }
}