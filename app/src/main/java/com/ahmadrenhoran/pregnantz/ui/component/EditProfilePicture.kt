package com.ahmadrenhoran.pregnantz.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.PhotoCamera
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.ahmadrenhoran.pregnantz.R

@Composable
fun EditProfilePicture() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentWidth(align = Alignment.CenterHorizontally)
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo_light),
            contentDescription = "Profile Picture",
            modifier = Modifier
                .size(128.dp)
                .clip(CircleShape)
                .background(color = Color.White)
        )
        IconButton(
            onClick = { /* action to edit profile picture */ },
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