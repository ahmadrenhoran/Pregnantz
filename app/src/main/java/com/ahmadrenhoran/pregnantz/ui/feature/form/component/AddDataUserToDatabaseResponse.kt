package com.ahmadrenhoran.pregnantz.ui.feature.form.component

import androidx.compose.runtime.*
import androidx.hilt.navigation.compose.hiltViewModel
import com.ahmadrenhoran.pregnantz.domain.model.Response
import com.ahmadrenhoran.pregnantz.ui.component.Dialog
import com.ahmadrenhoran.pregnantz.ui.component.ProgressBar
import com.ahmadrenhoran.pregnantz.ui.feature.form.FormViewModel

@Composable
fun AddDataUserToDatabaseResponse(
    viewModel: FormViewModel = hiltViewModel(),
    onSuccessAddData: (isAddSuccess: Boolean) -> Unit
) {
    when(val addDataUserToDatabaseResponse = viewModel.addDataUserToDatabaseResponse) {
        is Response.Loading -> ProgressBar()
        is Response.Success -> addDataUserToDatabaseResponse.data?.let { isAddSuccess ->
            LaunchedEffect(isAddSuccess) {
                onSuccessAddData(isAddSuccess)
            }
        }
        is Response.Failure -> addDataUserToDatabaseResponse.apply {
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