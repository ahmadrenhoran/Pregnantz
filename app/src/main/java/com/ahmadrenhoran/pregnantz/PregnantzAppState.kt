package com.ahmadrenhoran.pregnantz

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.ahmadrenhoran.pregnantz.ui.feature.PregnantzAuthScreen
import com.ahmadrenhoran.pregnantz.ui.feature.PregnantzHomeScreen

@Composable
fun rememberPregnantzAppState(
    navController: NavHostController = rememberNavController()
) =
    remember(navController) {
        PregnantzAppState(navController)
    }

@Stable
class PregnantzAppState(val navController: NavHostController) {
    val bottomBarTabs = PregnantzHomeScreen.values()
    private val bottomBarRoutes = bottomBarTabs.map { it.name }

    val shouldShowBottomBar: Boolean
        @Composable get() = navController
            .currentBackStackEntryAsState().value?.destination?.route in bottomBarRoutes
}

fun NavHostController.navigateToAndPopUpTo(destination: String, popUp: String) {
    this.navigate(route = destination) {
        popUpTo(popUp) {
            inclusive = true
        }
    }
}