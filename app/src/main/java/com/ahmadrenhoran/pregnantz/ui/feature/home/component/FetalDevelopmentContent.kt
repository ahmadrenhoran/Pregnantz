package com.ahmadrenhoran.pregnantz.ui.feature.home.component
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Remove
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ahmadrenhoran.pregnantz.domain.model.BabyData

@Composable
fun FetalDevelopmentContent(modifier: Modifier = Modifier, babyData: BabyData, currentWeek: Int) {
    var showDetailSize by remember {
        mutableStateOf(false)
    }
    var showBabyDesc by remember {
        mutableStateOf(false)
    }

    Card(modifier = modifier, elevation = CardDefaults.cardElevation(8.dp)) {
        Column(modifier = Modifier.padding(12.dp)) {
            Image(painter = painterResource(id = babyData.babyImage), contentDescription = "Fetal Development Image", modifier = Modifier
                .fillMaxWidth()
                .wrapContentSize(align = Alignment.Center)
                .size(300.dp))
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                Text(text = "Your baby at $currentWeek weeks", style = MaterialTheme.typography.displaySmall, fontSize = 16.sp)
                IconButton(onClick = { showBabyDesc = !showBabyDesc }) {
                    if (!showBabyDesc) {
                        Icon(
                            imageVector = Icons.Outlined.Add,
                            contentDescription = "Show Detail",
                        )
                    } else {
                        Icon(
                            imageVector = Icons.Outlined.Remove,
                            contentDescription = "Hide Detail",
                        )
                    }
                }
            }
            AnimatedVisibility(visible = showBabyDesc) {
                Text(text = babyData.babyDescription)
            }
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                Text(text = "Baby size at $currentWeek weeks", style = MaterialTheme.typography.displaySmall, fontSize = 16.sp)
                IconButton(onClick = { showDetailSize = !showDetailSize }) {
                    if (!showDetailSize) {
                        Icon(
                            imageVector = Icons.Outlined.Add,
                            contentDescription = "Show Detail",
                        )
                    } else {
                        Icon(
                            imageVector = Icons.Outlined.Remove,
                            contentDescription = "Hide Detail",
                        )
                    }
                }
            }
            AnimatedVisibility(visible = showDetailSize) {
                Column() {
                    Image(painter = painterResource(id = babyData.babySize.analogyImage!!), contentDescription = "Fetal Development Image", modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentSize(align = Alignment.Center)
                        .size(100.dp))
                    Text(text = babyData.babySize.analogy, modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center)
                    Text(text = "Length: ${babyData.babySize.lengthCm} cm")
                    Text(text = "Mass: ${babyData.babySize.massG} grams")
                }
            }

        }
    }
}