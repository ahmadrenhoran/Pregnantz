package com.ahmadrenhoran.pregnantz.ui.feature.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Done
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ahmadrenhoran.pregnantz.core.DueDateMenu
import com.ahmadrenhoran.pregnantz.core.Utils
import com.ahmadrenhoran.pregnantz.domain.model.Response
import com.ahmadrenhoran.pregnantz.domain.model.User
import com.ahmadrenhoran.pregnantz.ui.component.EditProfilePicture
import com.ahmadrenhoran.pregnantz.ui.component.MenuSelectAge
import com.ahmadrenhoran.pregnantz.ui.feature.form.component.AddImageToStorageResponse
import com.ahmadrenhoran.pregnantz.ui.feature.form.component.DueDate
import java.time.LocalDate


@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
    viewModel: ProfileViewModel = hiltViewModel(),
    onLogOut: () -> Unit
) {
    val uiState = viewModel.uiState.collectAsState().value
    when (val response = viewModel.getUserDataResponse) {
        is Response.Loading -> {}
        is Response.Success -> response.data.let {
            LaunchedEffect(it) {
                viewModel.setUser(it)
            }
        }
        is Response.Failure -> {}
    }


    val user = uiState.user

    Scaffold(
        modifier = modifier.padding(16.dp), bottomBar = {
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    viewModel.logOut()
                    onLogOut()
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color.Red),
                shape = MaterialTheme.shapes.small
            ) {
                Text(text = "Logout")
            }
        }, topBar = {

            Text(
                text = "Profile",
                fontSize = 24.sp,
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
        ) {
            EditProfilePicture(onUploadImage = { viewModel.addImageToStorage(it) }, user.photoUrl)
            Spacer(modifier = Modifier.height(24.dp))
            PersonalDataView(
                onUpdateClick = { viewModel.addDataUserToDb() },
                user = user,
                onValueChange = { viewModel.setUser(user.copy(name = it)) },
                // Age
                ageMenuExpand = uiState.isAgeMenuExpand,
                onAgeMenuExpandClick = { viewModel.setAgeMenuExpand(!uiState.isAgeMenuExpand) },
                onAgeMenuDismissRequest = { viewModel.setAgeMenuExpand(false) },
                onAgeMenuItemClick = {
                    viewModel.setUser(user.copy(age = it))
                    viewModel.setAgeMenuExpand(false)
                },
                // Due Date
                dueDate = user.dueDate,
                onDueDateChange = { viewModel.setUser(user.copy(dueDate = it)) },
                dueDateMenu = uiState.dueDateMenu,
                isDueDateMenuExpand = uiState.isDueDateMenuExpand,
                onDueDateMenuExpand = {
                    viewModel.setDueDateMenuExpand(!uiState.isDueDateMenuExpand)
                },
                onDueDateMenuDismiss = { viewModel.setDueDateMenuExpand(false) },
                onDueDateMenuItemClick = { viewModel.setDueDateMenu(it) },
                estimatedDueDate = Utils.TimeFormatter(LocalDate.parse(viewModel.getDueDate()))
            )

        }


    }

    AddImageToStorageResponse(onSuccessUploadImage = { uri ->
        viewModel.setUser(user.copy(photoUrl = uri.toString()))
    })


}

@Composable
fun PersonalDataView(
    onUpdateClick: () -> Unit,
    user: User,
    onValueChange: (String) -> Unit,
    ageMenuExpand: Boolean,
    onAgeMenuExpandClick: () -> Unit,
    onAgeMenuDismissRequest: () -> Unit,
    onAgeMenuItemClick: (String) -> Unit,
    dueDate: String,
    onDueDateChange: (String) -> Unit,
    dueDateMenu: DueDateMenu,
    isDueDateMenuExpand: Boolean,
    onDueDateMenuExpand: () -> Unit,
    onDueDateMenuDismiss: () -> Unit,
    onDueDateMenuItemClick: (DueDateMenu) -> Unit,
    estimatedDueDate: String
) {
    Spacer(
        modifier = Modifier
            .height(2.dp)
            .fillMaxWidth()
            .background(color = MaterialTheme.colorScheme.onBackground)
    )
    Spacer(modifier = Modifier.height(8.dp))
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Personal Data",
            style = MaterialTheme.typography.displaySmall,
            fontSize = 16.sp
        )

        IconButton(onClick = onUpdateClick) {
            Icon(
                imageVector = Icons.Outlined.Done,
                contentDescription = "Update Date",
                tint = Color.Green
            )
        }
    }
    Spacer(modifier = Modifier.height(4.dp))
    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        value = user.name,
        onValueChange = onValueChange,
        label = {
            Text(
                "Name",
                style = MaterialTheme.typography.bodyLarge
            )
        }, singleLine = true
    )

    MenuSelectAge(
        modifier = Modifier.fillMaxWidth(),
        age = user.age,
        expanded = ageMenuExpand,
        onExpandClick = onAgeMenuExpandClick,
        onDismissRequest = onAgeMenuDismissRequest,
        onMenuItemClick = onAgeMenuItemClick
    )

    DueDate(
        modifier = Modifier.fillMaxWidth(),
        dueDate = dueDate,
        onDueDateChange = onDueDateChange,
        dueDateMenu = dueDateMenu,
        dueDateMenuExpand = isDueDateMenuExpand,
        onExpandClick = onDueDateMenuExpand,
        onDismissRequest = onDueDateMenuDismiss,
        onMenuItemClick = onDueDateMenuItemClick
    )
    Spacer(modifier = Modifier.height(4.dp))
    Text(text = "Your due date is: $estimatedDueDate")
    Spacer(modifier = Modifier.height(8.dp))
    Spacer(
        modifier = Modifier
            .height(2.dp)
            .fillMaxWidth()
            .background(color = MaterialTheme.colorScheme.onBackground)
    )
}