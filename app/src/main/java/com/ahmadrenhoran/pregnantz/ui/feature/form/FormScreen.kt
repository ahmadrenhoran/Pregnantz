package com.ahmadrenhoran.pregnantz.ui.feature.form


import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ahmadrenhoran.pregnantz.R
import com.ahmadrenhoran.pregnantz.ui.component.EditProfilePicture


@Composable
fun FormScreen(modifier: Modifier = Modifier, viewModel: FormViewModel = hiltViewModel()) {



    Column(modifier = modifier.padding(24.dp)) {
        Text(text = "Form", style = MaterialTheme.typography.displayMedium)
        Text(text = "Personalize your app experience")
        Spacer(modifier = Modifier.padding(12.dp))
        EditProfilePicture()
        Spacer(modifier = Modifier.padding(12.dp))
        OutlinedTextField(value = "Name", onValueChange = {})
        Spacer(modifier = Modifier.padding(12.dp))



    }


}