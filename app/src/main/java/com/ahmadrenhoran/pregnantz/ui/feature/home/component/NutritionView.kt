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

@Composable
fun NutritionView(modifier: Modifier = Modifier) {
    Card(modifier = modifier.fillMaxWidth()) {
        var showMore by remember {
            mutableStateOf(false)
        }
        Column(modifier = Modifier.padding(12.dp)) {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                Text(text = "Nutrition", style = MaterialTheme.typography.displaySmall)
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
            Text(text = "- Folic Acid")
            Text(text = "- Calcium")
            Text(text = "- Vitamin D")
            Text(text = "- Protein")
            Text(text = "- Iron Nutrition 27 mg each day")

            Spacer(modifier = Modifier.height(8.dp))
            AnimatedVisibility(visible = showMore) {
                Column() {
                    Text(text = "There are 13 General Messages of Balanced Nutrition, namely:", style = MaterialTheme.typography.displaySmall, fontSize = 16.sp)

                    Text(text = "" +
                            "1 Eat a variety of foods\n" +

                            "2 Eat half of the carbohydrate source of energy needs\n" +

                            "3 Use iodized salt\n" +

                            "4 Eat food sources of iron nutrition\n" +

                            "5 Breastfeed the baby until the age of six months\n" +

                            "6 Get used to breakfast\n" +

                            "7 Drink clean, safe and sufficient amounts of water\n" +

                            "8 Avoid drinking alcoholic beverages\n" +

                            "9 Eat food that is safe for health\n" +
                            "10 Read labels on packaged foods\n" +
                            "11 Eat foods that meet energy needs\n" +
                            "12 Limit fat a quarter of the energy adequacy\n" +
                            "13 Be physically active and exercise regularly\n"
                    )
                }
            }
        }
    }
}