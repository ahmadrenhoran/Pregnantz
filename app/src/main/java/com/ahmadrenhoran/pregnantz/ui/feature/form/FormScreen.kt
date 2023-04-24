package com.ahmadrenhoran.pregnantz.ui.feature.form


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ahmadrenhoran.pregnantz.R
import com.ahmadrenhoran.pregnantz.domain.model.Response
import com.ahmadrenhoran.pregnantz.ui.component.EditProfilePicture
import com.ahmadrenhoran.pregnantz.ui.component.MenuSelectAge
import com.ahmadrenhoran.pregnantz.ui.feature.form.component.AddDataUserToDatabaseResponse
import com.ahmadrenhoran.pregnantz.ui.feature.form.component.AddImageToStorageResponse
import com.ahmadrenhoran.pregnantz.ui.feature.form.component.DueDate


@Composable
fun FormScreen(
    modifier: Modifier = Modifier,
    viewModel: FormViewModel = hiltViewModel(),
    onSuccessFilledForm: () -> Unit
) {
    val uiState = viewModel.uiState.collectAsState().value
    val scrollState = rememberScrollState()

    if (viewModel.addImageToStorageResponse != Response.Loading && viewModel.addDataUserToDatabaseResponse != Response.Loading) {
        Scaffold(modifier = modifier.padding(24.dp), bottomBar = {
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
        }) { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .verticalScroll(scrollState),
            ) {
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
                DueDate(
                    modifier = Modifier.fillMaxWidth(),
                    dueDate = uiState.date,
                    onDueDateChange = {
                        viewModel.setDate(it)
                    },
                    dueDateMenu = uiState.dueDateMenu,
                    dueDateMenuExpand = uiState.isDueDateMenuExpand,
                    onExpandClick = {
                        viewModel.setDueDateMenuExpand(!uiState.isDueDateMenuExpand)
                    },
                    onDismissRequest = {
                        viewModel.setDueDateMenuExpand(false)
                    },
                    onMenuItemClick = {
                        viewModel.setDueDateMenu(it)
                        viewModel.setDueDateMenuExpand(false)
                    })
                Spacer(modifier = Modifier.padding(12.dp))
                Text(modifier = Modifier, text = buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            color = MaterialTheme.colorScheme.onBackground
                        )
                    ) {
                        append("Congratulation \uD83C\uDF89\uD83C\uDF89\uD83C\uDF89! your due date is ")
                    }

                    withStyle(
                        style = SpanStyle(
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onBackground
                        )
                    ) {
                        append(viewModel.getDueDate())
                    }
                })
                Text(modifier = Modifier, text = buildAnnotatedString {
                    val week = pluralStringResource(id = R.plurals.pregnantProgressWeeks, viewModel.getDueWeek().toInt(), viewModel.getDueWeek().toInt())
                    val day = pluralStringResource(id = R.plurals.pregnantProgressDays, viewModel.getDueDay().toInt(), viewModel.getDueDay().toInt())
                    withStyle(
                        style = SpanStyle(
                            color = MaterialTheme.colorScheme.onBackground
                        )
                    ) {
                        append("You have ")
                    }

                    withStyle(
                        style = SpanStyle(
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onBackground
                        )
                    ) {
                        append(week)
                    }

                    if (viewModel.getDueDay() > 0) {
                        withStyle(
                            style = SpanStyle(
                                color = MaterialTheme.colorScheme.onBackground
                            )
                        ) {
                            append(" and ")
                        }

                        withStyle(
                            style = SpanStyle(
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onBackground
                            )
                        ) {
                            append(day)
                        }
                    }
                    withStyle(
                        style = SpanStyle(
                            color = MaterialTheme.colorScheme.onBackground
                        )
                    ) {
                        append(" to go")
                    }
                })

                Spacer(modifier = Modifier.padding(12.dp))
            }
        }
    }

    AddImageToStorageResponse(onSuccessUploadImage = { uri ->
        viewModel.setImageUri(uri)
    })

    AddDataUserToDatabaseResponse(onSuccessAddData = { isSuccessAddData ->
        if (isSuccessAddData) {
            onSuccessFilledForm()
        }
    })


}