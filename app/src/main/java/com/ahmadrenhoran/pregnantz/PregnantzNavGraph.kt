package com.ahmadrenhoran.pregnantz

import android.annotation.SuppressLint
import android.util.Log
import androidx.preference.PreferenceManager
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.ahmadrenhoran.pregnantz.core.Constants
import com.ahmadrenhoran.pregnantz.ui.article.ArticleScreen
import com.ahmadrenhoran.pregnantz.ui.component.PregnantzBottomNavigation
import com.ahmadrenhoran.pregnantz.ui.feature.PregnantzAuthScreen
import com.ahmadrenhoran.pregnantz.ui.feature.PregnantzHomeScreen
import com.ahmadrenhoran.pregnantz.ui.feature.authentication.LoginScreen
import com.ahmadrenhoran.pregnantz.ui.feature.authentication.RegisterScreen
import com.ahmadrenhoran.pregnantz.ui.feature.form.FormScreen
import com.ahmadrenhoran.pregnantz.ui.feature.home.HomeScreen
import com.ahmadrenhoran.pregnantz.ui.feature.splashscreen.SplashScreen
import com.ahmadrenhoran.pregnantz.ui.feature.tools.ToolsScreen
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

@SuppressLint("CommitPrefEdits")
@Composable
fun PregnantzNavGraph(modifier: Modifier = Modifier) {
    val appState = rememberPregnantzAppState()

    Scaffold(
        bottomBar = {
            if (appState.shouldShowBottomBar) {
                Log.d(Constants.LOGIN_SCREEN_TAG, "PregnantzNavGraph: ")
                PregnantzBottomNavigation(
                    navController = appState.navController,
                    items = Constants.BOTTOM_BAR_ITEM_LIST
                )
            }
            Log.d(Constants.LOGIN_SCREEN_TAG, "PregnantzNavGraph: tesss")
        }
    ) { innerPadding ->
        NavHost(
            modifier = modifier.padding(innerPadding),
            navController = appState.navController,
            startDestination = PregnantzAuthScreen.Splash.name
        ) {
            composable(route = PregnantzAuthScreen.Splash.name) {
                SplashScreen {
                    if (Firebase.auth.currentUser == null) {
                        appState.navController.navigateToAndPopUpTo(
                            PregnantzAuthScreen.Login.name,
                            PregnantzAuthScreen.Splash.name
                        )
                    } else { // User already log in
                        if (Firebase.auth.currentUser!!.displayName == null) { // if user has not filled the form
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
                        if (Firebase.auth.currentUser!!.displayName == null) { // if user has not filled the form
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

            composable(route = PregnantzHomeScreen.Home.name) {
                HomeScreen()
            }

            composable(route = PregnantzHomeScreen.Tools.name) {
                ToolsScreen()
            }

            composable(route = PregnantzHomeScreen.Article.name) {
                ArticleScreen()
            }
        }

    }
}