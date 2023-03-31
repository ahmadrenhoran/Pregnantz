package com.ahmadrenhoran.pregnantz.ui.feature.splashscreen

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ahmadrenhoran.pregnantz.R
import com.ahmadrenhoran.pregnantz.ui.theme.PregnantzTheme


@Composable
fun SplashScreen(modifier: Modifier = Modifier,onAnimationEnd: () -> Unit) {
    val opacity = remember { androidx.compose.animation.core.Animatable(0f) }

    LaunchedEffect(Unit) {
        opacity.animateTo(
            targetValue = 1f,
            animationSpec = tween(
                durationMillis = 1000,
                easing = LinearEasing
            )
        )

        onAnimationEnd()
    }

    val isDarkTheme = isSystemInDarkTheme()
    val image =
        painterResource(id = if (isDarkTheme) R.drawable.pregnantz_logo_dark else R.drawable.pregnantz_logo_light)

    Column(
        modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            modifier = Modifier.width(332.dp).height(312.dp).weight(0.8f),
            painter = image, contentDescription = "Logo",

        )
        Text(
            modifier = Modifier.alpha(opacity.value).weight(0.2f),
            text = buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        fontStyle = MaterialTheme.typography.displayLarge.fontStyle,
                        fontSize = 32.sp,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                ) {
                    append(stringResource(id = R.string.app_name))
                }

                withStyle(
                    style = SpanStyle(
                        fontStyle = MaterialTheme.typography.bodyLarge.fontStyle,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                ) {
                    append("\nMonitoring and Guiding")
                }
            }
        )

//        Text(
//            text = "Monitoring and Guiding",
//            style = MaterialTheme.typography.bodyLarge,
//            modifier = Modifier.alpha(opacity.value)
//        )

    }
}

@Preview(showBackground = true)
@Composable
fun SplashScreenPreview() {
    PregnantzTheme {
        SplashScreen {

        }
    }
}