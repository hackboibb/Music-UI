package com.example.musicappui.ui


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.IconButton
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text


import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.rememberScaffoldState

import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme


import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.musicappui.ui.theme.AccountDialog
import com.example.musicappui.ui.theme.AccountView
import com.example.musicappui.ui.theme.Browse
import com.example.musicappui.MainViewModel
import com.example.musicappui.R
import com.example.musicappui.Screen
import com.example.musicappui.ScreenInBottom

import com.example.musicappui.ScreenInDrawer
import com.example.musicappui.ui.theme.SubscriptionView
import com.example.musicappui.ui.theme.library
import com.example.musicappui.ui.theme.Home
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MainView() {

    val scaffoldState: ScaffoldState = rememberScaffoldState()
    val scope: CoroutineScope = rememberCoroutineScope()

    val isSheetFullScreen by remember { mutableStateOf(false) }
    val modifier = if (isSheetFullScreen) Modifier.fillMaxSize() else Modifier.fillMaxWidth()
    val modelsheetstate = androidx.compose.material.rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        confirmValueChange = { it != ModalBottomSheetValue.HalfExpanded })

    val roundedcornerRadius = if (isSheetFullScreen) 0.dp else 12.dp

    val viewmodel: MainViewModel = viewModel()

    val currentScreen = remember {
        viewmodel.currentscreen.value
    }


    val accountDialodopen = remember { mutableStateOf(false) }


    // Allow us to find out on which "View" we current are
    val controller: NavController = rememberNavController()
    val navBackStackEntry by controller.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val title = remember { mutableStateOf(currentScreen.title) }


    val bottomBar: @Composable () -> Unit = {

        if (currentScreen is Screen.drawerScreen || currentScreen == Screen.buttomScreen.Home) {
            BottomNavigation(Modifier.wrapContentSize(), backgroundColor = Color(0xff1DB954)) {
                ScreenInBottom.forEach { item ->

                    val isSelected = currentRoute == item.route
                    val tint =
                        if (isSelected) androidx.compose.ui.graphics.Color.White else androidx.compose.ui.graphics.Color.Black

                    BottomNavigationItem(
                        selected = currentRoute == item.broute,


                        onClick = {
                            controller.navigate(item.broute)
                            title.value = item.btitle

                        },

                        icon = {

                            Icon(
                                contentDescription = item.btitle,
                                painter = painterResource(id = item.icon),
                                tint = tint
                            )

                        },

                        label = {
                            Text(text = item.btitle, color = tint)

                        },

                        selectedContentColor = androidx.compose.ui.graphics.Color.White,
                        unselectedContentColor = androidx.compose.ui.graphics.Color.Black

                    )
                }


            }
        }

    }

    ModalBottomSheetLayout(

        sheetState = modelsheetstate,

        sheetShape = RoundedCornerShape(
            topStart = roundedcornerRadius,
            topEnd = roundedcornerRadius
        ),

        sheetContent = {
            MoreBottomSheet(modifier = modifier)
        }) {
        Scaffold(

            bottomBar = bottomBar,
            backgroundColor = Color(0xFF191414),


            topBar = {

                TopAppBar(title = { Text(title.value, color = Color.White) },
                    backgroundColor = Color(0xFF1DB954),
                    actions = {

                        IconButton(onClick = {
                            scope.launch {
                                if (modelsheetstate.isVisible) modelsheetstate.hide() else modelsheetstate.show()
                            }

                        }) {

                            Icon(imageVector = Icons.Default.MoreVert, contentDescription = null)

                        }
                    },

                    navigationIcon = {
                        IconButton(onClick = {

                            //opening the drawer

                            scope.launch {


                                scaffoldState.drawerState.open()
                            }
                        })


                        {
                            Icon(
                                imageVector = Icons.Default.AccountCircle,
                                contentDescription = "menu"
                            )


                        }

                    }
                )
            }, scaffoldState = scaffoldState,

            drawerContent = {
                Column(
                    modifier = Modifier
                        .background(Color(0xFF191414))
                        .fillMaxSize()
                ) {

                    LazyColumn(Modifier.padding(16.dp)) {

                        items(ScreenInDrawer) {


                                item ->
                            drawerItem(selected = currentRoute == item.droute, item = item) {

                                scope.launch {

                                    scaffoldState.drawerState.close()
                                }
                                if (item.droute == "add_account") {
                                    accountDialodopen.value = true
                                } else {
                                    controller.navigate(item.droute)
                                    title.value = item.dtitle

                                }

                            }
                        }

                    }
                }
            }


        )
        {
            Navigation(navController = controller, viewModel = viewmodel, pd = it)
            AccountDialog(dialogOPen = accountDialodopen)
        }

    }


}

@Composable
fun drawerItem(

    selected: Boolean,
    item: Screen.drawerScreen,
    onDrawerItemClicked: () -> Unit
) {
    val background =
        if (selected) Color(0xFF1DB954) else Color.Black
    Row(
        Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 16.dp)
            .clickable {
                onDrawerItemClicked()
            }) {
        Icon(
            painter = painterResource(id = item.icon),
            contentDescription = item.dtitle,
            Modifier.padding(end = 8.dp, top = 4.dp)
        )
        Text(
            text = item.dtitle,
            style = MaterialTheme.typography.headlineMedium,
            color = Color(0xffffffff)

        )
    }

}

@Composable
fun MoreBottomSheet(modifier: Modifier) {
    Box(
        Modifier
            .fillMaxWidth()
            .height(300.dp)
            .background(
                MaterialTheme.colorScheme.background
            )
    ) {
        Column(modifier = modifier.padding(16.dp), verticalArrangement = Arrangement.SpaceBetween) {
            Row(modifier = modifier.padding(16.dp)) {
                Icon(
                    modifier = Modifier.padding(end = 8.dp),
                    painter = painterResource(id = R.drawable.baseline_settings_24),
                    contentDescription = "Settings"
                )
                Text(
                    text = "Settings",
                    fontSize = 20.sp,
                    color = androidx.compose.ui.graphics.Color.White
                )
            }
            Row(modifier = modifier.padding(16.dp)) {
                Icon(
                    modifier = Modifier.padding(end = 8.dp),
                    painter = painterResource(id = R.drawable.ic_baseline_share_24),
                    contentDescription = "Share"
                )
                Text(
                    text = "Share",
                    fontSize = 20.sp,
                    color = androidx.compose.ui.graphics.Color.White
                )

                androidx.compose.material.Text(
                    text = "Share",
                    fontSize = 20.sp,
                    color = androidx.compose.ui.graphics.Color.White
                )
            }
            Row(modifier = modifier.padding(16.dp)) {
                Icon(
                    modifier = Modifier.padding(end = 8.dp),
                    painter = painterResource(id = R.drawable.ic_help_green),
                    contentDescription = "Help"
                )
                Text(
                    text = "Help",
                    fontSize = 20.sp,
                    color = androidx.compose.ui.graphics.Color.White
                )
            }
        }
    }
}


@Composable

fun Navigation(navController: NavController, viewModel: MainViewModel, pd: PaddingValues) {


    NavHost(
        navController = navController as NavHostController,
        startDestination = Screen.drawerScreen.Account.route,
        modifier = Modifier.padding(pd)
    )

    {

        composable(Screen.buttomScreen.Home.broute) {


            Home()
        }
        composable(Screen.buttomScreen.Library.broute) {
            library()
        }
        composable(Screen.buttomScreen.Browse.broute) {
            Browse()
        }

        composable(Screen.drawerScreen.Account.route) {

            AccountView()

        }

        composable(Screen.drawerScreen.Subscription.route) {

            SubscriptionView()

        }


    }
}

@Preview
@Composable

fun MainViewPreview() {

    MainView()
}