package com.example.musicappui.ui.theme

import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import com.example.musicappui.R

@Composable

fun Browse(){

    val Bcategoires = listOf("Hits", "Sunday Special", "Yoga", "Workouts", "Chill" , "Romantic")

    LazyVerticalGrid(GridCells.Fixed(2)) {

        items(Bcategoires){

            cat ->
            BrowseScreen(cat = cat, drawable = R.drawable.baseline_library_music_24)
        }
        
    }


}