package com.ahmadrenhoran.pregnantz.ui.feature.authentication

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ahmadrenhoran.pregnantz.R
import com.ahmadrenhoran.pregnantz.core.Constants
import com.ahmadrenhoran.pregnantz.domain.model.Response
import com.ahmadrenhoran.pregnantz.ui.feature.authentication.component.*


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun RegisterScreen(
    modifier: Modifier = Modifier,
    viewModel: AuthViewModel = hiltViewModel(),
    onClickableTextRegister: (Int) -> Unit,
    onSuccessSignUp: () -> Unit
) {
    val uiState = viewModel.uiState.collectAsState().value
    val focusManager = LocalFocusManager.current
    val keyboard = LocalSoftwareKeyboardController.current
    val isEmailValid = viewModel.isEmailValid.collectAsState().value
    val isPasswordValid = viewModel.isPasswordValid.collectAsState().value
    if (viewModel.emailResponseSignUp != Response.Loading) {
        Column(
            modifier = modifier
                .padding(start = 24.dp, end = 24.dp, bottom = 12.dp)
                .fillMaxWidth()
                .fillMaxHeight(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                modifier = Modifier
                    .width(243.dp)
                    .height(233.dp),
                painter = painterResource(id = R.drawable.mother_with_baby2),
                contentDescription = ""
            )
            Text(
                text = stringResource(R.string.register),
                modifier = Modifier.fillMaxWidth(),
                style = MaterialTheme.typography.displayLarge,
                fontWeight = FontWeight.SemiBold,
                fontSize = 24.sp
            )
            Spacer(modifier = Modifier.height(16.dp))
            EmailField(
                email = uiState.email,
                onValueChange = { viewModel.setEmail(it) }, isError = !isEmailValid,
                supportingText = "Email format is not valid",
                keyboardActions = KeyboardActions(
                    onNext = {
                        focusManager.moveFocus(FocusDirection.Down)
                    }
                )
            )
            PasswordField(
                password = uiState.password,
                onValueChange = {
                    viewModel.setPassword(it)
                },
                passwordVisibility = uiState.passwordVisibility,
                onClickPasswordVisibility = { viewModel.setPasswordVisibility(!uiState.passwordVisibility) },
                isError = !isPasswordValid,
                supportingText = "The password should consist of at least 8 characters with a minimum of 1 number.",
                keyboardActions = KeyboardActions(
                    onDone = {

                    }
                )
            )
            Spacer(modifier = Modifier.height(24.dp))
            Button(
                modifier = Modifier
                    .fillMaxWidth(), shape = MaterialTheme.shapes.small,
                onClick = {
                    keyboard?.hide()
                    viewModel.register()
                }

            ) {
                Text(
                    text = stringResource(id = R.string.register),
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 20.sp,
                    color = MaterialTheme.colorScheme.onPrimary
                )
            }
            FormTextNav(
                modifier = Modifier
                    .fillMaxHeight()
                    .wrapContentHeight(Alignment.Bottom),
                firstText = "I already have an account? ",
                secondText = "Login",
                onClick = onClickableTextRegister
            )
        }
    }
    SignUpWithEmailResponse(onSuccessSignUp = { signUp ->
        if (signUp) { // if the Response.Success when sign up is true
            onSuccessSignUp()
        }
    })

}