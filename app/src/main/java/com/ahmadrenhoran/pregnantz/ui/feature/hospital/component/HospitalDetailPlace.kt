package com.ahmadrenhoran.pregnantz.ui.feature.hospital.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
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
import com.ahmadrenhoran.pregnantz.ui.component.ProgressBar
import com.ahmadrenhoran.pregnantz.ui.feature.authentication.AuthViewModel
import com.ahmadrenhoran.pregnantz.ui.feature.hospital.HospitalLocationViewModel


@Composable
fun HospitalDetailPlace(
    modifier: Modifier = Modifier,
    isShow: Boolean = false, place: Place,
    onDismissRequest: () -> Unit
) {
    AnimatedVisibility(visible = isShow) {
        Dialog(onDismissRequest = onDismissRequest) {
            Card() {
                Column(modifier = modifier.padding(24.dp)) {
                    Text(text = place.name.toString(), fontSize = 24.sp, fontWeight = FontWeight.SemiBold)
                    Text(text = place.address.toString())
                }
            }
        }
    }

}

@Composable
fun HospitalDetailPlaceResponse(
    viewModel: HospitalLocationViewModel = hiltViewModel(),
    modifier: Modifier = Modifier,
    isShow: Boolean = false,
    onDismissRequest: () -> Unit
) {
    when (val signInResponse = viewModel.getDetailPlaceResponse) {
        is Response.Loading -> {}
        is Response.Success -> signInResponse.data.let { place ->
            HospitalDetailPlace(
                isShow = isShow,
                onDismissRequest = onDismissRequest,
                place = place
            )

        }
        is Response.Failure -> signInResponse.apply {
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