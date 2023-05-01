package com.ahmadrenhoran.pregnantz.domain.model

import com.google.android.gms.maps.model.LatLng

data class Place(val id: String? = null, val name: String? = null, val address: String? = null, val latLng: LatLng? = null, val noTelp: String = "112")