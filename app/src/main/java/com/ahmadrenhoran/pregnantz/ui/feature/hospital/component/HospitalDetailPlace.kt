package com.ahmadrenhoran.pregnantz.ui.feature.hospital.component

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import com.ahmadrenhoran.pregnantz.domain.model.Place
import com.ahmadrenhoran.pregnantz.domain.model.Response
import com.ahmadrenhoran.pregnantz.ui.feature.hospital.HospitalLocationViewModel


@Composable
fun HospitalDetailPlace(
    modifier: Modifier = Modifier,
    isShow: Boolean = false, place: Place,
    context: Context,
    onDismissRequest: () -> Unit
) {

    AnimatedVisibility(visible = isShow) {
        Dialog(onDismissRequest = onDismissRequest) {
            Card() {
                Column(modifier = modifier.padding(24.dp)) {
                    Text(
                        text = place.name.toString(),
                        fontSize = 24.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                    Text(text = place.address.toString())
                    Button(modifier = Modifier.fillMaxWidth(), onClick = {
                        val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:${place.noTelp}"))
                        context.startActivity(intent)
                    }) {
                        Text(text = "Call")
                    }
                }
            }
        }
    }

}

@Composable
fun HospitalDetailPlaceResponse(
    viewModel: HospitalLocationViewModel = hiltViewModel(),
    isShow: Boolean = false,
    context: Context,
    onDismissRequest: () -> Unit
) {
    when (val getDetailPlaceResponse = viewModel.getDetailPlaceResponse) {
        is Response.Loading -> {}
        is Response.Success -> getDetailPlaceResponse.data.let { place ->
            HospitalDetailPlace(
                isShow = isShow,
                onDismissRequest = onDismissRequest,
                place = place, context = context
            )

        }
        is Response.Failure -> getDetailPlaceResponse.apply {
            var errorDialogPopupShown by remember { mutableStateOf(false) }
            var errorDesc by remember { mutableStateOf("") }

            LaunchedEffect(e) {
                errorDesc = e.localizedMessage
                errorDialogPopupShown = true
            }
            if (errorDialogPopupShown) {
                com.ahmadrenhoran.pregnantz.ui.component.Dialog(
                    text = errorDesc, onConfirm = { errorDialogPopupShown = false }
                ) { errorDialogPopupShown = false }
            }
        }
    }
}