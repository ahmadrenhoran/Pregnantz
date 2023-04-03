package com.ahmadrenhoran.pregnantz

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.ahmadrenhoran.pregnantz.ui.feature.PregnantzAuthScreen
import com.ahmadrenhoran.pregnantz.ui.feature.authentication.LoginScreen
import com.ahmadrenhoran.pregnantz.ui.feature.authentication.RegisterScreen
import com.ahmadrenhoran.pregnantz.ui.feature.splashscreen.SplashScreen

@Composable
fun PregnantzNavGraph(modifier: Modifier = Modifier) {
    val appState = rememberPregnantzAppState()
    Scaffold { innerPadding ->
        NavHost(
            modifier = modifier.padding(innerPadding),
            navController = appState.navController,
            startDestination = PregnantzAuthScreen.Splash.name
        ) {
            composable(route = PregnantzAuthScreen.Splash.name) {
                SplashScreen {
                    appState.navController.navigate(route = PregnantzAuthScreen.Login.name) {
                        popUpTo(PregnantzAuthScreen.Splash.name) {
                            inclusive = true
                        }
                    }
                }
            }
            composable(route = PregnantzAuthScreen.Login.name) {
                LoginScreen(
                    onClickableTextRegister = { appState.navController.navigate(route = PregnantzAuthScreen.Register.name) },
                    onSuccessSignIn = {

                    }
                )
            }
            composable(route = PregnantzAuthScreen.Register.name) {
                RegisterScreen(
                    onClickableTextRegister = { appState.navController.navigateUp() },
                    onSuccessSignUp = {
                        appState.navController.navigateUp()
                    })
            }
            composable(route = PregnantzAuthScreen.Form.name) {

            }
        }

    }
}