package com.ahmadrenhoran.pregnantz.ui.feature

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Backpack
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Newspaper
import androidx.compose.ui.graphics.vector.ImageVector
import com.ahmadrenhoran.pregnantz.R

enum class PregnantzAuthScreen(val route: String) {
    Login(route = "LoginScreen"),
    Register(route = "RegisterScreen"),
    Form(route = "RegisterScreen"),
    Splash(route = "SplashScreen")
}

enum class PregnantzHomeScreen(
    @StringRes val title: Int,
    val route: String,
    val icon: ImageVector
) {
    Home(title = R.string.home, "Home", Icons.Outlined.Home),
    Tools(title = R.string.tools, "Tool", Icons.Outlined.Backpack),
    Article(title = R.string.article, "Article", Icons.Outlined.Newspaper),
}

enum class PregnantzToolsScreen(val route: String) {
    HospitalLocationScreen(route = "HospitalLocationScreen"),
    WeightScreen(route = "WeightScreen"),
    PregnancyCalculator(route = "PregnancyCalculator"),
}

enum class PregnantzOtherScreen(val route: String) {
    ProfileScreen(route = "ProfileScreen")
}