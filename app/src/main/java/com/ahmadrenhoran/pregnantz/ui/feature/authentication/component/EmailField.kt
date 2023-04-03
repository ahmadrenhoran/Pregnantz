package com.ahmadrenhoran.pregnantz.ui.feature.authentication.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AlternateEmail
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import com.ahmadrenhoran.pregnantz.R


@Composable
fun EmailField(
    modifier: Modifier = Modifier,
    email: String,
    onValueChange: (String) -> Unit,
    supportingText: String = "",
    isError: Boolean = false,
    keyboardActions: KeyboardActions
) {
    OutlinedTextField(
        modifier = modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.small,
        value = email,
        onValueChange = onValueChange,
        label = {
            Text(
                stringResource(R.string.email),
                style = MaterialTheme.typography.bodyLarge
            )
        },
        placeholder = {
            Text(
                stringResource(R.string.enter_email),
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
            Icon(
                Icons.Outlined.AlternateEmail,
                contentDescription = stringResource(R.string.email),
            )
        },
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Email,
            imeAction = ImeAction.Next
        ),
        keyboardActions = keyboardActions,
    )
}