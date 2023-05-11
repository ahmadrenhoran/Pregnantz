package com.ahmadrenhoran.pregnantz.ui.feature.home.component


import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Remove
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.ahmadrenhoran.pregnantz.domain.model.Response
import com.ahmadrenhoran.pregnantz.ui.feature.home.component.DueDateView
import com.ahmadrenhoran.pregnantz.ui.feature.home.component.NutritionView
import java.time.DayOfWeek
import java.time.LocalDate
@Composable
fun ExerciseView(modifier: Modifier = Modifier) {
    var showMore by remember {
        mutableStateOf(false)
    }
    Card(modifier = modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(12.dp)) {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                Text(text = "Exercise", style = MaterialTheme.typography.displaySmall)
                IconButton(onClick = { showMore = !showMore }) {
                    if (!showMore) {
                        Icon(
                            imageVector = Icons.Outlined.Add,
                            contentDescription = "Show More",
                        )
                    } else {
                        Icon(
                            imageVector = Icons.Outlined.Remove,
                            contentDescription = "Show More",
                        )
                    }
                }
            }
            if (LocalDate.now().dayOfWeek == DayOfWeek.TUESDAY) {
                Text(text = "- Warm up")
                Text(text = "- Walking 30 min 🚶‍")
                Text(text = "- Stationary bicycling 10 min")
                Text(text = "- Cool down")
            } else if (LocalDate.now().dayOfWeek == DayOfWeek.THURSDAY) {
                Text(text = "- Warm up")
                Text(text = "- Running 10 min 🏃‍️")
                Text(text = "- Yoga 30 min 🧘‍")
                Text(text = "- Cool down")
            } else if (LocalDate.now().dayOfWeek == DayOfWeek.SATURDAY) {
                Text(text = "- Warm up")
                Text(text = "- Yoga 30 min 🧘‍")
                Text(text = "- Stationary bicycling 10 min")
                Text(text = "- Cool down")
            } else if (LocalDate.now().dayOfWeek == DayOfWeek.SUNDAY) {
                Text(text = "- Warm up")
                Text(text = "- Walking 20 min 🚶‍")
                Text(text = "- Yoga 20 min 🧘‍")
                Text(text = "- Cool down")
            } else {
                Text(text = "No exercise today")
            }
            Spacer(modifier = Modifier.height(8.dp))
            AnimatedVisibility(visible = showMore) {
                Column() {
                    Text(
                        text = "Warning signs to terminate exercise while pregnant :",
                        style = MaterialTheme.typography.displaySmall,
                        fontSize = 16.sp
                    )
                    Text(text = "" +
                            "• Bleeding from the vagina\n" +

                            "• Feeling dizzy or faint\n" +

                            "• Shortness of breath before starting exercise\n" +

                            "• Chest pain\n" +

                            "• Headache\n" +

                            "• Muscle weakness\n" +

                            "• Calf pain or swelling\n" +

                            "• Regular, painful contractions of the uterus\n" +

                            "• Fluid gushing or leaking from the vagina\n"
                    )
                }

            }
        }
    }
}