package com.ahmadrenhoran.pregnantz.ui.feature.authentication.component

import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.ahmadrenhoran.pregnantz.core.Constants
import com.ahmadrenhoran.pregnantz.domain.model.Response
import com.ahmadrenhoran.pregnantz.ui.component.ProgressBar
import com.ahmadrenhoran.pregnantz.ui.feature.authentication.AuthViewModel
import kotlin.math.sign

@Composable
fun SignUpWithEmailResponse(
    viewModel: AuthViewModel = hiltViewModel(),
    onSuccessSignUp: (signUp: Boolean) -> Unit
) {
    when (val signUpResponse = viewModel.emailResponseSignUp) {
        is Response.Loading -> ProgressBar()
        is Response.Success -> signUpResponse.data.let { signUp ->
            LaunchedEffect(signUp) {
                onSuccessSignUp(signUp)
            }
        }
        is Response.Failure -> {
            var errorDialogPopupShown by remember { mutableStateOf(false) }
            if (errorDialogPopupShown) {
                Dialog(
                    text = signUpResponse.e.localizedMessage ?: ""
                ) { errorDialogPopupShown = false }
            }
        }
    }
}