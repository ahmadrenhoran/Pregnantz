package com.ahmadrenhoran.pregnantz

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.ahmadrenhoran.pregnantz.ui.theme.PregnantzTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PregnantzTheme {
                PregnantzNavGraph(context = this)
            }
        }
    }
}

