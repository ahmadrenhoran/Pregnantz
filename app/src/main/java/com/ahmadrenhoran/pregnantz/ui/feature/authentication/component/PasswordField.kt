package com.ahmadrenhoran.pregnantz.ui.feature.authentication.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import com.ahmadrenhoran.pregnantz.R

@Composable
fun PasswordField(
    modifier: Modifier = Modifier,
    password: String,
    onValueChange: (String) -> Unit,
    passwordVisibility: Boolean,
    onClickPasswordVisibility: () -> Unit,
    supportingText: String = "",
    isError: Boolean = false,
    keyboardActions: KeyboardActions
) {
    OutlinedTextField(
        modifier = modifier.fillMaxWidth(), singleLine = true,
        shape = MaterialTheme.shapes.small,
        value = password,
        onValueChange = onValueChange,
        label = {
            Text(
                stringResource(R.string.password),
                style = MaterialTheme.typography.bodyLarge
            )
        },
        placeholder = {
            Text(
                stringResource(R.string.enter_password),
                style = MaterialTheme.typography.bodyLarge
            )
        },
        supportingText = {
            if (isError) {
                Text(text = supportingText)
            }
        },
        isError = isError,
        trailingIcon = {
            IconButton(onClick = onClickPasswordVisibility) {
                Icon(
                    imageVector = if (passwordVisibility) Icons.Filled.VisibilityOff else Icons.Filled.Visibility,
                    contentDescription = if (passwordVisibility) stringResource(R.string.hide_password) else stringResource(
                        R.string.show_password
                    )
                )
            }
        },
        visualTransformation = if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Password,
            imeAction = ImeAction.Done
        ),
        keyboardActions = keyboardActions,
    )
}