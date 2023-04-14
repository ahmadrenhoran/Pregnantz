package com.ahmadrenhoran.pregnantz.ui.feature.form.component

import android.net.Uri
import androidx.compose.runtime.*
import androidx.hilt.navigation.compose.hiltViewModel
import com.ahmadrenhoran.pregnantz.domain.model.Response
import com.ahmadrenhoran.pregnantz.ui.component.Dialog
import com.ahmadrenhoran.pregnantz.ui.component.ProgressBar
import com.ahmadrenhoran.pregnantz.ui.feature.form.FormViewModel

@Composable
fun AddImageToStorageResponse(
    viewModel: FormViewModel = hiltViewModel(),
    onSuccessUploadImage: (downloadUrl: Uri) -> Unit
) {
    when(val addImageToStorageResponse = viewModel.addImageToStorageResponse) {
        is Response.Loading -> ProgressBar()
        is Response.Success -> addImageToStorageResponse.data?.let { downloadUrl ->
            LaunchedEffect(downloadUrl) {
                onSuccessUploadImage(downloadUrl)
            }
        }
        is Response.Failure -> addImageToStorageResponse.apply {
            var errorDialogPopupShown by remember { mutableStateOf(false) }
            var errorDesc by remember { mutableStateOf("") }
            LaunchedEffect(e) {
                errorDesc = e.localizedMessage
                errorDialogPopupShown = true
            }
            if (errorDialogPopupShown) {
                Dialog(
                    text = errorDesc
                ) { errorDialogPopupShown = false }
            }
        }
    }
}