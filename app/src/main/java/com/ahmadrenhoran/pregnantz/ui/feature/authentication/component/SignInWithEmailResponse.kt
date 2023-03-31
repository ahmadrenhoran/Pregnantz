package com.ahmadrenhoran.pregnantz.ui.feature.authentication.component

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.ahmadrenhoran.pregnantz.domain.model.Response
import com.ahmadrenhoran.pregnantz.domain.repository.SignInWithEmailResponse
import com.ahmadrenhoran.pregnantz.ui.component.ProgressBar
import com.ahmadrenhoran.pregnantz.ui.feature.authentication.AuthViewModel

@Composable
fun SignInWithEmailResponse(
    viewModel: AuthViewModel = hiltViewModel(),
    onSuccessSignIn: (signIn: Boolean) -> Unit
) {
    when (val signInResponse = viewModel.emailResponseSignIn) {
        is Response.Loading -> ProgressBar()
        is Response.Success -> signInResponse.data.let { signIn ->
            LaunchedEffect(signIn) {
                onSuccessSignIn(signIn)
            }
        }
        is Response.Failure -> {
            var errorDialogPopupShown by remember { mutableStateOf(false) }
            if (errorDialogPopupShown) {
                Dialog(
                    text = signInResponse.e.localizedMessage ?: ""
                ) { errorDialogPopupShown = false }
            }
        }
    }
}