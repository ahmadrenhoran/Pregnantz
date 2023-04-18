package com.ahmadrenhoran.pregnantz.ui.feature.tools

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.location.LocationManager
import android.net.Uri
import android.provider.Settings
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.app.ActivityCompat.shouldShowRequestPermissionRationale
import androidx.core.location.LocationManagerCompat
import androidx.hilt.navigation.compose.hiltViewModel
import com.ahmadrenhoran.pregnantz.core.Constants
import com.ahmadrenhoran.pregnantz.ui.component.Dialog
import com.ahmadrenhoran.pregnantz.ui.feature.tools.component.HospitalDialogEvent
import com.ahmadrenhoran.pregnantz.ui.feature.tools.component.HospitalTool
import com.ahmadrenhoran.pregnantz.ui.feature.tools.component.PregnancyCalculatorTool
import com.ahmadrenhoran.pregnantz.ui.feature.tools.component.WeightTool
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState

@SuppressLint("ServiceCast")
@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun ToolsScreen(
    modifier: Modifier = Modifier,
    onHospitalClick: () -> Unit,
    viewModel: ToolsViewModel = hiltViewModel(),
) {

    val uiState = viewModel.uiState.collectAsState()

    // Location permission state
    val locationPermissionsState = rememberMultiplePermissionsState(Constants.LOCATION_PERMISSIONS)


    val locationManager =
        LocalContext.current.getSystemService(Context.LOCATION_SERVICE) as LocationManager
    var gpsEnabled by remember {
        mutableStateOf(locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER))
    }





    Column(modifier = modifier.padding(16.dp)) {
        Text(text = "Tools", fontSize = 24.sp, color = MaterialTheme.colorScheme.onBackground)
        Spacer(modifier = modifier.height(8.dp))
        WeightTool(onClick = {

        }, onClickAddWeight = {

        })
        Spacer(modifier = modifier.height(8.dp))
        Row() {
            HospitalTool(
                modifier = Modifier
                    .weight(1f)
                    .height(240.dp)
            ) {
                locationPermissionsState.launchMultiplePermissionRequest()
                if (!locationPermissionsState.shouldShowRationale) {
                    viewModel.setShouldShowPermissionDialog(true)
                } else {
                    viewModel.setShouldShowPermissionDialog(false)
                }

                if (locationPermissionsState.allPermissionsGranted) {
                    viewModel.setShouldShowPermissionDialog(false)
                    if (gpsEnabled) { // If User, not activate the GPS
                        onHospitalClick()
                    } else {
                        viewModel.setShouldShowGPSDialog(true)
                    }

                }
            }
            Spacer(modifier = Modifier.width(8.dp))
            PregnancyCalculatorTool(
                modifier = Modifier
                    .weight(1f)
                    .height(240.dp)
            ) {

            }

        }
    }

    HospitalDialogEvent(
        uiState = uiState,
        setGPSvalue = {
            gpsEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
        })


}




