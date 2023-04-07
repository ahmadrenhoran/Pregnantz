package com.ahmadrenhoran.pregnantz.core

import com.ahmadrenhoran.pregnantz.ui.feature.PregnantzHomeScreen

object Constants {

    // Firebase RTDB Ref
    const val DB_USERS_REF = "users"

    // Firebase Storage Ref
    const val STORAGE_PROFILE_PICTURES = "profilePictures"
    const val STORAGE_PICTURES = "pictures"

    // SHARED PREFERENCES
    const val IS_FORM_FILLED = "isFormFilled"

    // List Menu Home
    val BOTTOM_BAR_ITEM_LIST = listOf(
        PregnantzHomeScreen.Home,
        PregnantzHomeScreen.Tools,
        PregnantzHomeScreen.Article
    )

    // List age
    val AGE_LIST = listOf("16-20", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50+")

    // TAG
    const val REGISTER_SCREEN_TAG = "registerScreenTag"
    const val LOGIN_SCREEN_TAG = "loginScreenTag"
    const val AUTH_REPOSITORY_IMPL = "AuthRepositoryImplTag"
}