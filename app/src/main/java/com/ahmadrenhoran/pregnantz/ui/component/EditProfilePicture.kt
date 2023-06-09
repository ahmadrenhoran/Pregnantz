package com.ahmadrenhoran.pregnantz.ui.component

import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.PhotoCamera
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.ahmadrenhoran.pregnantz.R
import com.ahmadrenhoran.pregnantz.core.Constants

@Composable
fun EditProfilePicture(onUploadImage: (imageUri: Uri) -> Unit, currentPicture: String) {
    var selectedImageUri by remember {
        mutableStateOf<Uri?>(null)
    }

    val singlePhotoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = { uri ->
            selectedImageUri = uri
            Log.d(Constants.ALL_TAG, "EditProfilePicture: " + uri)
            if (uri != null) {
                onUploadImage(uri)
            }
        }
    )

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentWidth(align = Alignment.CenterHorizontally)
    ) {
        AsyncImage(
            model = currentPicture.ifEmpty { R.drawable.logo_light },
            contentDescription = "Profile Picture",
            modifier = Modifier
                .size(128.dp)
                .clip(CircleShape)
                .background(color = Color.White),
            contentScale = ContentScale.Crop
        )
        IconButton(
            onClick = {
                singlePhotoPickerLauncher.launch(
                    PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                )
            },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .size(32.dp)
                .border(
                    width = 1.dp,
                    color = Color.LightGray,
                    shape = CircleShape
                )
                .background(color = Color.White, shape = CircleShape)
                .wrapContentSize()
        ) {
            Icon(
                imageVector = Icons.Outlined.PhotoCamera,
                contentDescription = "Edit Profile Picture",
                tint = Color.DarkGray,
                modifier = Modifier.size(16.dp)
            )
        }
    }
}

@Composable
fun EditProfilePicture2(onUploadImage: (imageUri: Uri) -> Unit) {
    var selectedImageUri by remember {
        mutableStateOf<Uri?>(null)
    }

    val singlePhotoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = { uri ->
            selectedImageUri = uri
            if (uri != null) {
                onUploadImage(uri)
            }
        }
    )
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentWidth(align = Alignment.CenterHorizontally)
    ) {
        AsyncImage(
            model = selectedImageUri ?: R.drawable.logo_light,
            contentDescription = "Profile Picture",
            modifier = Modifier
                .size(128.dp)
                .clip(CircleShape)
                .background(color = Color.White),
            contentScale = ContentScale.Crop
        )
        IconButton(
            onClick = {
                singlePhotoPickerLauncher.launch(
                    PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                )
            },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .size(32.dp)
                .border(
                    width = 1.dp,
                    color = Color.LightGray,
                    shape = CircleShape
                )
                .background(color = Color.White, shape = CircleShape)
                .wrapContentSize()
        ) {
            Icon(
                imageVector = Icons.Outlined.PhotoCamera,
                contentDescription = "Edit Profile Picture",
                tint = Color.DarkGray,
                modifier = Modifier.size(16.dp)
            )
        }
    }
}