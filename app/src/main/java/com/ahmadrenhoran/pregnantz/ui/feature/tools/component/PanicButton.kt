package com.ahmadrenhoran.pregnantz.ui.feature.tools.component

import android.Manifest
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.telephony.SmsManager
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import com.ahmadrenhoran.pregnantz.domain.model.PanicNumber
import com.ahmadrenhoran.pregnantz.domain.model.Response
import com.ahmadrenhoran.pregnantz.ui.feature.tools.ToolsViewModel
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.google.accompanist.permissions.rememberPermissionState

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun PanicButton(
    modifier: Modifier = Modifier,
    context: Context,
    viewModel: ToolsViewModel = hiltViewModel()
) {
    val locationPermissionsState = rememberPermissionState(Manifest.permission.SEND_SMS)
    val uiState = viewModel.uiState.collectAsState().value
    Row(modifier = modifier) {
        Button(
            modifier = Modifier.weight(0.4f),
            shape = MaterialTheme.shapes.small,
            onClick = { viewModel.setShouldShowPanicDialog(!uiState.shouldShowPanicDialog) }) {
            Icon(imageVector = Icons.Default.Add, contentDescription = "Add contact number")
        }
        Spacer(modifier = Modifier.width(8.dp))
        Button(
            modifier = Modifier.weight(1f),
            colors = ButtonDefaults.buttonColors(Color(0xFFD34949)),
            shape = MaterialTheme.shapes.small,
            onClick = {
                if (locationPermissionsState.status.isGranted) {
                    val smsManager = SmsManager.getDefault()
                    val phoneNumbers = uiState.listPanicNumbers.map { it.number }
                    val message = "Hello, ini adalah pesan SMS dari aplikasi saya"
                    for (phoneNumber in phoneNumbers) {
                        smsManager.sendTextMessage(phoneNumber, null, message, null, null)
                    }
                } else {
                    locationPermissionsState.launchPermissionRequest()
                }

            }

        ) {
            Text(text = "Panic Button")
        }

        Spacer(modifier = Modifier.width(8.dp))
        Button(
            modifier = Modifier.weight(0.5f),
            colors = ButtonDefaults.buttonColors(Color(0xFFB00D0D)),
            shape = MaterialTheme.shapes.small,
            onClick = {
                val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:${112}"))
                context.startActivity(intent)
            }) {
            Text(text = "112")
        }
    }

    PanicDialog(
        onClick = {
            if (uiState.listPanicNumbers.size <= 5) {
                viewModel.insertPanicNumber()
                viewModel.getListPanicNumbers()
            }
        },
        onDelete = {
            viewModel.deletePanicNumber(it)
            viewModel.getListPanicNumbers()
        },
        onValueChange = { viewModel.setPanicNumber(it) },
        number = uiState.panicNumber,
        phoneNumbers = uiState.listPanicNumbers,
        onDismissRequest = { viewModel.setShouldShowPanicDialog(false) },
        isShow = uiState.shouldShowPanicDialog
    )

    when (val listPanicNumbersResponse = viewModel.getListPanicNumbersResponse) {
        is Response.Loading -> {}
        is Response.Success -> listPanicNumbersResponse.data.let { list ->
            LaunchedEffect(list) {
                viewModel.setListPanicNumbers(list)
            }
        }
        is Response.Failure -> listPanicNumbersResponse.apply {
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PanicDialog(
    modifier: Modifier = Modifier,
    isShow: Boolean,
    number: String,
    phoneNumbers: List<PanicNumber>,
    onClick: () -> Unit,
    onDelete: (String) -> Unit,
    onValueChange: (String) -> Unit,
    onDismissRequest: () -> Unit
) {
    AnimatedVisibility(visible = isShow) {
        Dialog(onDismissRequest = onDismissRequest) {
            Card() {
                Column(
                    modifier = modifier.padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = "Phone Numbers", style = MaterialTheme.typography.displaySmall)

                    Text(text = "Max (5)")

                    Spacer(modifier = Modifier.height(4.dp))
                    LazyColumn(modifier = Modifier.fillMaxWidth()) {
                        items(phoneNumbers) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(text = it.number)
                                Card(onClick = { onDelete(it.id) }) {
                                    Icon(
                                        imageVector = Icons.Outlined.Close,
                                        contentDescription = "Delete Number"
                                    )
                                }
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(12.dp))
                    Row(
                        modifier = Modifier, verticalAlignment = Alignment.CenterVertically
                    ) {
                        OutlinedTextField(
                            modifier = Modifier
                                .weight(1f),
                            value = number,
                            onValueChange = onValueChange,
                            keyboardOptions = KeyboardOptions.Default.copy(
                                keyboardType = KeyboardType.Decimal,
                                imeAction = ImeAction.Done,
                            ),
                            placeholder = {
                                Text(text = "Phone Number")
                            }
                        )
                        Spacer(modifier = Modifier.width(12.dp))
                        Button(
                            modifier = Modifier
                                .weight(0.4f), shape = MaterialTheme.shapes.small, onClick = onClick
                        ) {
                            Icon(
                                imageVector = Icons.Default.Add,
                                contentDescription = "Add contact number"
                            )
                        }
                    }
                }
            }
        }
    }

}