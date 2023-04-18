package com.ahmadrenhoran.pregnantz.ui.feature.tools.component

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.location.LocationManager
import android.net.Uri
import android.provider.Settings
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.ahmadrenhoran.pregnantz.ui.component.Dialog
import com.ahmadrenhoran.pregnantz.ui.feature.hospital.HospitalLocationUiState
import com.ahmadrenhoran.pregnantz.ui.feature.tools.ToolsUiState
import com.ahmadrenhoran.pregnantz.ui.feature.tools.ToolsViewModel

@Composable
fun HospitalDialogEvent(uiState: State<ToolsUiState>, viewModel: ToolsViewModel = hiltViewModel(), setGPSvalue: () -> Unit) {
    val activity = LocalContext.current as Activity

    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        // Update gps value
        setGPSvalue()
        viewModel.setShouldShowGPSDialog(false)
        viewModel.setShouldShowPermissionDialog(false)
    }


    if (uiState.value.shouldShowPermissionDialog) {
        Dialog(
            text = "It seems you permanently declined location permission. " +
                    "You can go to the app settings to grant it.",
            confirmButtonText = "Grant", onConfirm = {
                // Open App Setting
                val intent = Intent(
                    Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                    Uri.fromParts("package", activity.packageName, null)
                )
                launcher.launch(intent)

            }
        ) {
            viewModel.setShouldShowPermissionDialog(false)
        }
    }

    if (uiState.value.shouldShowGPSDialog) {
        Dialog(
            text =
            "Please enable GPS to use this feature.",
            confirmButtonText = "Ok", onConfirm = {
                // Open location setting
                val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
                launcher.launch(intent)
            }, onDismiss = {
                viewModel.setShouldShowGPSDialog(false)
            }
        )
    }
}