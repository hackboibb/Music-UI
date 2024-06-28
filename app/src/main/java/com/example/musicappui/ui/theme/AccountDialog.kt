package com.example.musicappui.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.TextField
import androidx.compose.material.primarySurface

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties

@Composable

fun AccountDialog( dialogOPen: MutableState<Boolean>){
    if(dialogOPen.value == true){

        AlertDialog(
            onDismissRequest = {
                                       dialogOPen.value = false

                                       },

            confirmButton = {
                
               TextButton(onClick = { dialogOPen.value   = false }) {
                   Text("Confirm")
                   
               }
            },

            dismissButton = {

                TextButton(onClick = { dialogOPen.value = false }) {

                    Text("Dismiss")

                }
            },

            title = {
                Text(text = " Add Account" , modifier = Modifier.padding(16.dp))
            },

            text = {

                Column(
                    modifier = Modifier
                        .wrapContentHeight()
                        .padding(horizontal = 16.dp),
                    verticalArrangement = Arrangement.Center
                )
                {
                    TextField(
                        value = "",
                        onValueChange = { /* Handle email input change */ },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp),
                        label = { Text("Email") }
                    )
                    TextField(
                        value = "",
                        onValueChange = { /* Handle password input change */ },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp),
                        label = { Text("Password") }
                    )




                }
            },
            modifier = Modifier.fillMaxWidth().background(MaterialTheme.colors.primarySurface),

            shape = RoundedCornerShape(8.dp),
            backgroundColor = Color.White,
            properties = DialogProperties(
                dismissOnBackPress = true,
                dismissOnClickOutside = true
            ),







        )


    }

}