package com.ahmadrenhoran.pregnantz.core

import android.Manifest
import com.ahmadrenhoran.pregnantz.ui.feature.PregnantzHomeScreen
import com.google.android.libraries.places.api.model.Place
import java.time.LocalDate

object Constants {

    // Firebase RTDB Ref
    const val DB_USERS_REF = "users"

    // Firebase Storage Ref
    const val STORAGE_PROFILE_PICTURES = "profilePictures"
    const val STORAGE_PICTURES = "pictures"

    // Google Map Places
    // Specify the fields
    val PLACE_FIELDS = listOf(
        Place.Field.NAME,
        Place.Field.ADDRESS,
        Place.Field.LAT_LNG,
        Place.Field.RATING,
        Place.Field.PHONE_NUMBER
    )

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
    private val DUE_DATE_BOUNDARY_FIRST_DAY_LAST_PERIOD =
        LocalDate.now().minusWeeks(42)..LocalDate.now()
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

    // Trimester
    val FIRST_TRIMESTER = 1..12
    val SECOND_TRIMESTER = 13..27
    val THIRD_TRIMESTER = 28..42

    // TAG
    const val REGISTER_SCREEN_TAG = "registerScreenTag"
    const val LOGIN_SCREEN_TAG = "loginScreenTag"
    const val TOOLS_SCREEN_TAG = "toolsScreenTag"
    const val AUTH_REPOSITORY_IMPL = "AuthRepositoryImplTag"
    const val ALL_TAG = "ALL_TAG"

    // Location Permission
    val LOCATION_PERMISSIONS = listOf(
        Manifest.permission.ACCESS_FINE_LOCATION
    )




}

data class DueDateMenu(val name: String, val boundary: ClosedRange<LocalDate>)
data class PregnancyData(
//    @DrawableRes val babyImage: Int,
//    val babyInfo: String,
    val nutritionInfo: List<String>,
    val exercisesInfo: List<String>
)

data class ExerciseInfo(val exerciseName: String, val duration: Int)