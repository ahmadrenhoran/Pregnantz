package com.ahmadrenhoran.pregnantz.ui.feature.home.component


import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Remove
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ahmadrenhoran.pregnantz.ui.feature.home.HomeViewModel
import java.time.DayOfWeek
import java.time.LocalDate

@Composable
fun ExerciseView(modifier: Modifier = Modifier, exercise: String) {
    var showWarningSign by remember {
        mutableStateOf(false)
    }
    var showHowMuchExercise by remember {
        mutableStateOf(false)
    }
    Card(modifier = modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(12.dp)) {
            Text(text = "Exercise", style = MaterialTheme.typography.displaySmall)
            Text(text = exercise)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "How much exercise?", style = MaterialTheme.typography.displaySmall, fontSize = 16.sp)
                IconButton(onClick = { showHowMuchExercise = !showHowMuchExercise }) {
                    if (!showHowMuchExercise) {
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
            AnimatedVisibility(visible = showHowMuchExercise) {
                Text(
                    text = "During pregnancy, it is recommended for pregnant women to engage in at least 150 minutes of moderate-intensity aerobic activity per week. This includes activities that involve rhythmic movements of large muscles, such as brisk walking and general gardening tasks. The goal is to raise the heart rate and start sweating while still being able to carry on a conversation.\n" +
                            "\n" +
                            "Newcomers to exercise should start slowly, with as little as 5 minutes a day, and gradually increase their activity level. Those who were already active before pregnancy can continue their regular workouts with their ob-gyn's approval. It's important to monitor weight changes and adjust calorie intake if necessary to ensure proper nourishment.")
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "Warning sign to stop?", style = MaterialTheme.typography.displaySmall, fontSize = 16.sp)
                IconButton(onClick = { showWarningSign = !showWarningSign }) {
                    if (!showWarningSign) {
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
            AnimatedVisibility(visible = showWarningSign) {
                Column() {
                    Text(
                        text = "Warning signs to terminate exercise while pregnant :",
                        style = MaterialTheme.typography.displaySmall,
                        fontSize = 16.sp
                    )
                    Text(
                        text = "" +
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