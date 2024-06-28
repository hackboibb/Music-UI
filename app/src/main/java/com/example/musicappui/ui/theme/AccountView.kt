package com.example.musicappui.ui.theme

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.musicappui.R


@Composable
fun AccountView() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row {
                Icon(
                    imageVector = Icons.Default.AccountCircle,
                    contentDescription = "Account Icon",
                    modifier = Modifier.padding(8.dp)
                )

                Column {
                    Text("Khalid_Tutorial" , color = Color(0xffffffff))
                    Text("@khalid123", color = Color(0xffffffff))
                }
            }

            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                    contentDescription = "Arrow Icon"
                )
            }
        }



        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),

        ) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_music_video_24),
                contentDescription = "Music Icon",
                modifier = Modifier.padding(8.dp)
            )

            Text(
                text = "My Music",
                modifier = Modifier.padding( 8.dp)
                , color = Color(0xffffffff)
            )
        }

        Divider()
    }
}