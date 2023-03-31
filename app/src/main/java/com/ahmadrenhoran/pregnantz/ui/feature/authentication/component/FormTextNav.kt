package com.ahmadrenhoran.pregnantz.ui.feature.authentication.component

import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle

@Composable
fun FormTextNav(modifier: Modifier, firstText: String, secondText: String, onClick: (Int) -> Unit) {
    ClickableText(
        buildAnnotatedString {
            withStyle(
                style = SpanStyle(
                    color = MaterialTheme.colorScheme.onBackground
                )
            ) {
                append(firstText)
            }

            withStyle(
                style = SpanStyle(
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onBackground
                )
            ) {
                append(secondText)
            }
        },
        onClick = onClick,
        modifier = modifier
    )
}