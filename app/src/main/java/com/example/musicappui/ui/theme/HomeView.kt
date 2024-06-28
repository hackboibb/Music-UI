package com.example.musicappui.ui.theme

import android.media.RouteListingPreference
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.musicappui.R

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Home() {
    val categories = listOf(
        "New Release" to listOf("Hits", "Sunday Special", "Yoga", "Hits" , "Hits"),
        "Top" to listOf("Workouts", "Chill","Workouts", "Chill","Workouts", "Chill"),
        "Favorite" to listOf("Romantic","Workouts", "Chill","Workouts", "Chill","Workouts", "Chill")
    )

    LazyColumn {
        categories.forEach { (category, items) ->
            stickyHeader {
                Text(
                    text = category,
                    modifier = Modifier.padding(16.dp),
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            }
            item {
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
                ) {
                    items(items) { item ->
                        BrowseScreen(cat = item, drawable = getDrawableForCategory(item))
                    }
                }
            }
        }
    }
}

@Composable
fun BrowseScreen(cat: String, drawable: Int) {
    Card(
        modifier = Modifier
            .size(200.dp)
            .clickable { /* Perform click operation */ }
            .padding(4.dp),
        border = BorderStroke(3.dp, Color.White)
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = drawable),
                contentDescription = null,
                modifier = Modifier.size(150.dp)
            )
            Text(
                text = cat,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(vertical = 8.dp)
            )
        }
    }
}

// Mock function to get drawable resource based on category
fun getDrawableForCategory(category: String): Int {
    return when (category) {
        "Hits" -> R.drawable.img4
        "Sunday Special" -> R.drawable.immg_2
        "Yoga" -> R.drawable.img_6
        "Workouts" -> R.drawable.img_6
        "Chill" -> R.drawable.zayn
        "Romantic" -> R.drawable.img_6  // Example, replace with actual drawables
        else -> R.drawable.ic_baseline_album_24 // Replace with appropriate default drawable
    }
}
