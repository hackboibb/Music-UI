package com.example.musicappui

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class MainViewModel: ViewModel() {

    private val _currentScreen : MutableState<Screen> = mutableStateOf(Screen.drawerScreen.AddAccount)

     val currentscreen: MutableState<Screen>
         get() = _currentScreen

    fun setScreen(screen: Screen){
        _currentScreen.value = screen
    }

}