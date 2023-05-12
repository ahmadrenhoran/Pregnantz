package com.ahmadrenhoran.pregnantz.domain.model

import androidx.annotation.DrawableRes

data class BabyData(@DrawableRes val babyImage: Int, val babySize: BabySize, val babyDescription: String)

data class BabySize(@DrawableRes val analogyImage: Int? = null, val analogy: String, val lengthCm: Float, val massG: Float)