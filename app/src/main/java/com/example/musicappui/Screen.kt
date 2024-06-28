package com.example.musicappui

import androidx.annotation.DrawableRes



sealed class Screen(val title: String , val route: String) {

    sealed class buttomScreen(val btitle:String , val broute:String , @DrawableRes val icon: Int):Screen( btitle , broute){

        object Home: buttomScreen("Home" , "home",R.drawable.baseline_account_box_24)

        object Library: buttomScreen("Library","library",R.drawable.baseline_video_library_24)

        object Browse:buttomScreen("Browse","browse",R.drawable.baseline_apps_24)

    }



    sealed class drawerScreen(val dtitle:String , val droute:String , @DrawableRes val icon: Int) :Screen ( dtitle , droute){

        object Account:drawerScreen(
            "Account",
            "account",
            R.drawable.baseline_account_box_24
        )

        object Subscription:drawerScreen(
            "Subscription",
            "subscribe",
            R.drawable.baseline_library_music_24
        )

        object AddAccount:drawerScreen(
            "Add Account",
            "add_account",
            R.drawable.baseline_person_add_alt_1_24

        )
    }
}

val ScreenInBottom =  listOf(

    Screen.buttomScreen.Home,
    Screen.buttomScreen.Browse,
    Screen.buttomScreen.Library
)

val ScreenInDrawer = listOf(
    Screen.drawerScreen.Account,
    Screen.drawerScreen.Subscription,
    Screen.drawerScreen.AddAccount
)