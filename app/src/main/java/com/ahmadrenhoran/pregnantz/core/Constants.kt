package com.ahmadrenhoran.pregnantz.core

import android.Manifest
import com.ahmadrenhoran.pregnantz.ui.feature.PregnantzHomeScreen
import java.time.LocalDate

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
    val AGE_LIST = listOf(
        "16-20",
        "20",
        "21",
        "22",
        "23",
        "24",
        "25",
        "26",
        "27",
        "28",
        "29",
        "30",
        "31",
        "32",
        "33",
        "34",
        "35",
        "36",
        "37",
        "38",
        "39",
        "40",
        "41",
        "42",
        "43",
        "44",
        "45",
        "46",
        "47",
        "48",
        "49",
        "50+"
    )

    // Due date boundary
    private val DUE_DATE_BOUNDARY_FIRST_DAY_LAST_PERIOD = LocalDate.now().minusWeeks(42)..LocalDate.now()
    private val DUE_DATE_BOUNDARY_ESTIMATED_DUE_DATE =
        LocalDate.now()..LocalDate.now().plusWeeks(42)


    // List menu due date
    val FIRST_DAY_OF_LAST_PERIOD = "First day of last period"
    val ESTIMATED_DUE_DATE = "Estimated due date"

    val DUE_DATE_MENU_LIST =
        listOf(
            DueDateMenu(FIRST_DAY_OF_LAST_PERIOD, DUE_DATE_BOUNDARY_FIRST_DAY_LAST_PERIOD),
            DueDateMenu(ESTIMATED_DUE_DATE, DUE_DATE_BOUNDARY_ESTIMATED_DUE_DATE)
        )


    // TAG
    const val REGISTER_SCREEN_TAG = "registerScreenTag"
    const val LOGIN_SCREEN_TAG = "loginScreenTag"
    const val TOOLS_SCREEN_TAG = "toolsScreenTag"
    const val AUTH_REPOSITORY_IMPL = "AuthRepositoryImplTag"

    // Location Permission
    val LOCATION_PERMISSIONS = listOf(
        Manifest.permission.ACCESS_FINE_LOCATION
    )

}

data class DueDateMenu(val name: String, val boundary: ClosedRange<LocalDate>)