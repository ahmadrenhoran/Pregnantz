package com.ahmadrenhoran.pregnantz

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.ahmadrenhoran.pregnantz.core.Constants
import com.ahmadrenhoran.pregnantz.ui.component.PregnantzBottomNavigation
import com.ahmadrenhoran.pregnantz.ui.feature.PregnantzAuthScreen
import com.ahmadrenhoran.pregnantz.ui.feature.PregnantzHomeScreen
import com.ahmadrenhoran.pregnantz.ui.feature.PregnantzOtherScreen
import com.ahmadrenhoran.pregnantz.ui.feature.PregnantzToolsScreen
import com.ahmadrenhoran.pregnantz.ui.feature.article.ArticleScreen
import com.ahmadrenhoran.pregnantz.ui.feature.authentication.LoginScreen
import com.ahmadrenhoran.pregnantz.ui.feature.authentication.RegisterScreen
import com.ahmadrenhoran.pregnantz.ui.feature.calculator.CalculatorScreen
import com.ahmadrenhoran.pregnantz.ui.feature.form.FormScreen
import com.ahmadrenhoran.pregnantz.ui.feature.home.HomeScreen
import com.ahmadrenhoran.pregnantz.ui.feature.hospital.HospitalLocationScreen
import com.ahmadrenhoran.pregnantz.ui.feature.hospital.HospitalLocationViewModel
import com.ahmadrenhoran.pregnantz.ui.feature.profile.ProfileScreen
import com.ahmadrenhoran.pregnantz.ui.feature.splashscreen.SplashScreen
import com.ahmadrenhoran.pregnantz.ui.feature.tools.ToolsScreen
import com.ahmadrenhoran.pregnantz.ui.feature.weight.WeightScreen
import com.google.android.gms.location.LocationServices
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


@SuppressLint("CommitPrefEdits")
@Composable
fun PregnantzNavGraph(modifier: Modifier = Modifier, context: Context) {
    val appState = rememberPregnantzAppState()

    Scaffold(
        bottomBar = {
            if (appState.shouldShowBottomBar) {
                PregnantzBottomNavigation(
                    navController = appState.navController,
                    items = Constants.BOTTOM_BAR_ITEM_LIST
                )
            }
        }
    ) { innerPadding ->
        NavHost(
            modifier = modifier.padding(innerPadding),
            navController = appState.navController,
            startDestination = PregnantzAuthScreen.Splash.name
        ) {
            // Auth
            composable(route = PregnantzAuthScreen.Splash.name) {
                SplashScreen {
                    if (Firebase.auth.currentUser == null) {
                        appState.navController.navigateToAndPopUpTo(
                            PregnantzAuthScreen.Login.name,
                            PregnantzAuthScreen.Splash.name
                        )
                    } else if (Firebase.auth.currentUser != null) { // User already log in
                        if (Firebase.auth.currentUser!!.displayName?.isEmpty() == true) { // if user has not filled the form
                            appState.navController.navigateToAndPopUpTo(
                                PregnantzAuthScreen.Form.name,
                                PregnantzAuthScreen.Splash.name
                            )

                        } else {
                            appState.navController.navigateToAndPopUpTo(
                                PregnantzHomeScreen.Home.name,
                                PregnantzAuthScreen.Splash.name
                            )
                        }
                    }
                }
            }
            composable(route = PregnantzAuthScreen.Login.name) {
                LoginScreen(
                    onClickableTextRegister = { appState.navController.navigate(route = PregnantzAuthScreen.Register.name) },
                    onSuccessSignIn = {
                        if (Firebase.auth.currentUser!!.displayName?.isEmpty() == true) { // if user has not filled the form
                            appState.navController.navigateToAndPopUpTo(
                                PregnantzAuthScreen.Form.name,
                                PregnantzAuthScreen.Login.name
                            )
                        } else {
                            appState.navController.navigateToAndPopUpTo(
                                PregnantzHomeScreen.Home.name,
                                PregnantzAuthScreen.Login.name
                            )
                        }
                    }
                )
            }
            composable(route = PregnantzAuthScreen.Register.name) {
                RegisterScreen(
                    onClickableTextRegister = { appState.navController.navigateUp() },
                    onSuccessSignUp = {
                        appState.navController.navigate(route = PregnantzAuthScreen.Form.name)
                    })
            }
            composable(route = PregnantzAuthScreen.Form.name) {
                FormScreen {
                    appState.navController.navigate(PregnantzHomeScreen.Home.name) {
                        popUpTo(PregnantzAuthScreen.Form.name) {
                            inclusive = true
                        }
                        popUpTo(PregnantzAuthScreen.Register.name) {
                            inclusive = true
                        }
                        popUpTo(PregnantzAuthScreen.Login.name) {
                            inclusive = true
                        }

                    }
                }
            }

            // Home
            composable(route = PregnantzHomeScreen.Home.name) {
                HomeScreen(onProfileClick = { appState.navController.navigate(PregnantzOtherScreen.ProfileScreen.name) })
            }

            // Article
            composable(route = PregnantzHomeScreen.Article.name) {
                ArticleScreen()
            }

            composable(route = PregnantzHomeScreen.Tools.name) {

                val hospitalViewModel: HospitalLocationViewModel = hiltViewModel()

                val fusedLocationProviderClient =
                    LocationServices.getFusedLocationProviderClient(context)
                ToolsScreen(
                    onHospitalClick = {
                        val result =
                            hospitalViewModel.getDeviceLocation(fusedLocationProviderClient)
                        if (result) {
                            appState.navController.navigate(PregnantzToolsScreen.HospitalLocationScreen.name)
                        }
                    }, context = context, onWeightClick = {
                        appState.navController.navigate(PregnantzToolsScreen.WeightScreen.name)
                    }, onCalculatorClick = {
                        appState.navController.navigate(PregnantzToolsScreen.PregnancyCalculator.name)
                    })

            }


            // Tools
            composable(route = PregnantzToolsScreen.HospitalLocationScreen.name) {
                HospitalLocationScreen(context = context)
            }

            composable(route = PregnantzToolsScreen.WeightScreen.name) {
                WeightScreen()
            }

            composable(route = PregnantzToolsScreen.PregnancyCalculator.name) {
                CalculatorScreen()
            }

            // Other
            composable(route = PregnantzOtherScreen.ProfileScreen.name) {
                ProfileScreen(onLogOut = {

                    appState.navController.navigate(PregnantzAuthScreen.Splash.name)
                })
            }
        }

    }
}