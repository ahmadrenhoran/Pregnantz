package com.ahmadrenhoran.pregnantz.ui.feature.authentication.component

import androidx.compose.runtime.*
import androidx.hilt.navigation.compose.hiltViewModel
import com.ahmadrenhoran.pregnantz.domain.model.Response
import com.ahmadrenhoran.pregnantz.ui.component.Dialog
import com.ahmadrenhoran.pregnantz.ui.component.ProgressBar
import com.ahmadrenhoran.pregnantz.ui.feature.authentication.AuthViewModel

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
        is Response.Failure -> signUpResponse.apply {
            var errorDialogPopupShown by remember { mutableStateOf(false) }
            var errorDesc by remember { mutableStateOf("") }
            LaunchedEffect(e) {
                errorDesc = e.localizedMessage
                errorDialogPopupShown = true
            }
            if (errorDialogPopupShown) {
                Dialog(
                    text = errorDesc, onConfirm = { errorDialogPopupShown = false }
                ) { errorDialogPopupShown = false }
            }
        }
    }
}