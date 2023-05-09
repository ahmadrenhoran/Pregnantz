package com.ahmadrenhoran.pregnantz.ui.feature.weight.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog

@Composable
fun WeightDialog(
    modifier: Modifier = Modifier,
    isShow: Boolean = false,
    weightKg: Float,
    onClick: () -> Unit,
    onValueChange: (String) -> Unit,
    onDismissRequest: () -> Unit
) {

    AnimatedVisibility(visible = isShow) {
        Dialog(onDismissRequest = onDismissRequest) {
            Card() {
                Column(
                    modifier = modifier
                        .padding(24.dp)
                        .fillMaxWidth()
                        .wrapContentWidth(align = Alignment.CenterHorizontally)
                ) {
                    TextField(
                        value = weightKg.toString(),
                        onValueChange = onValueChange,
                        keyboardOptions = KeyboardOptions.Default.copy(
                            keyboardType = KeyboardType.Decimal,
                            imeAction = ImeAction.Done,
                        ),
                        singleLine = true,
                        textStyle = TextStyle(fontSize = 60.sp, textAlign = TextAlign.Center)
                    )
                    Button(modifier = Modifier.fillMaxWidth(), onClick = onClick) {
                        Text(text = "Add Weight")
                    }
                }
            }
        }
    }

}