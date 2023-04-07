package com.ahmadrenhoran.pregnantz.ui.feature.form


import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ahmadrenhoran.pregnantz.domain.model.Response
import com.ahmadrenhoran.pregnantz.ui.component.EditProfilePicture
import com.ahmadrenhoran.pregnantz.ui.component.MenuSelectAge
import com.ahmadrenhoran.pregnantz.ui.feature.form.component.AddDataUserToDatabaseResponse
import com.ahmadrenhoran.pregnantz.ui.feature.form.component.AddImageToStorage


@Composable
fun FormScreen(
    modifier: Modifier = Modifier,
    viewModel: FormViewModel = hiltViewModel(),
    onSuccessFilledForm: () -> Unit
) {
    val uiState = viewModel.uiState.collectAsState().value

    if (viewModel.addImageToStorageResponse != Response.Loading || viewModel.addDataUserToDatabaseResponse != Response.Loading) {
        Column(modifier = modifier.padding(24.dp)) {
            Text(text = "Form", style = MaterialTheme.typography.displayMedium)
            Text(text = "Personalize your app experience")
            Spacer(modifier = Modifier.padding(12.dp))
            EditProfilePicture(onUploadImage = { uri ->
                viewModel.addImageToStorage(uri)
            })
            Spacer(modifier = Modifier.padding(12.dp))
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = uiState.name,
                onValueChange = { viewModel.setName(it) },
                label = {
                    Text(
                        "Name",
                        style = MaterialTheme.typography.bodyLarge
                    )
                },
                placeholder = {
                    Text(
                        "Enter your name",
                        style = MaterialTheme.typography.bodyLarge
                    )
                }, maxLines = 1
            )
            Spacer(modifier = Modifier.padding(12.dp))
            MenuSelectAge(modifier = Modifier.fillMaxWidth(),
                age = uiState.age,
                expanded = uiState.isAgeMenuExpand,
                onExpandClick = { viewModel.setAgeMenuExpand(!uiState.isAgeMenuExpand) },
                onDismissRequest = { viewModel.setAgeMenuExpand(false) },
                onMenuItemClick = { age ->
                    viewModel.setAge(age)
                    viewModel.setAgeMenuExpand(false)
                })
            Spacer(modifier = Modifier.padding(12.dp))
            Button(
                modifier = Modifier
                    .fillMaxWidth(), shape = MaterialTheme.shapes.small,
                onClick = {
                    viewModel.addDataUserToDatabase()
                }

            ) {
                Text(
                    text = "Done",
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 20.sp,
                    color = MaterialTheme.colorScheme.onPrimary
                )
            }

        }
    }

    AddImageToStorage(onSuccessUploadImage = { uri ->
        viewModel.setImageUri(uri)
    })

    AddDataUserToDatabaseResponse(onSuccessAddData = { isSuccessAddData ->
        if (isSuccessAddData) {
            onSuccessFilledForm()
        }
    })



}