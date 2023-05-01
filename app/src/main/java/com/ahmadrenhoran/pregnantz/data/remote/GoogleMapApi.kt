package com.ahmadrenhoran.pregnantz.data.remote

import com.ahmadrenhoran.pregnantz.BuildConfig
import com.ahmadrenhoran.pregnantz.data.model.maps.PlacesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface GoogleMapApi {

    @GET("json?keyword=rumah+sakit&radius=5000&type=hospital&rankBy=distance&name=rumah+sakit")
    suspend fun getNearbyHospital(@Query("key") apiKey: String = BuildConfig.MAPS_API_KEY, @Query("location") location: String): Response<PlacesResponse>
}