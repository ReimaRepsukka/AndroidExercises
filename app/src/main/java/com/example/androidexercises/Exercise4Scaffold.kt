package com.example.androidexercises

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.launch

@Composable
fun Task4() {

    val navController = rememberNavController()
    val scState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))

    Scaffold(
        scaffoldState = scState,
        topBar = { MyTopBar(scState) },
        bottomBar = { MyBottomBar(navController) },
        drawerContent = { MyDrawerLayout(navController, scState) }
    ) {
        NavHost(navController = navController, startDestination = "wifi") {
            composable(route = "wifi") { ColorView(Color.Cyan) }
            composable(route = "home") { ColorView(Color.LightGray) }
            composable(route = "music") { ColorView(Color.Yellow) }
        }
    }
}

@Composable
fun ColorView(color: Color) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color)
    )
}


@Composable
fun MyTopBar(scState: ScaffoldState) {

    val scope = rememberCoroutineScope()

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFD8AF24))
            .padding(5.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(R.drawable.ic_baseline_menu_24),
            contentDescription = "",
            modifier = Modifier.clickable {
                scope.launch {
                    scState.drawerState.open()
                }
            })
        SearchInputField()
    }
}

@Composable
fun SearchInputField() {
    OutlinedTextField(
        value = "",
        onValueChange = {},
        trailingIcon = {
            Icon(
                painter = painterResource(id = R.drawable.ic_baseline_search_24),
                contentDescription = ""
            )
        },
        modifier = Modifier
            .border(1.dp, Color.Black)
            .background(Color.White)
    )
}

@Composable
fun MyBottomBar(navController: NavHostController) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFD8AF24))
            .padding(10.dp, 5.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        BottomIcon(R.drawable.ic_baseline_wifi_24, navController, "wifi" )
        BottomIcon(R.drawable.ic_baseline_home_24, navController, "home" )
        BottomIcon(R.drawable.ic_baseline_library_music_24, navController, "music" )
    }
}

@Composable
fun BottomIcon(resId: Int, navController: NavHostController, route: String) {
    Icon(
        painter = painterResource(resId),
        contentDescription = "",
        modifier = Modifier.clickable { navController.navigate(route) }
    )
}

@Composable
fun MyDrawerLayout(navController: NavHostController, scState: ScaffoldState) {
    val scope = rememberCoroutineScope()

    //Added just one navigation link to the drawer view
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Text(text = "Wifi settings", fontSize = 26.sp,
            modifier = Modifier
                .clickable {
                    navController.navigate("wifi")
                    scope.launch {
                        scState.drawerState.close()
                    }
                })
    }
}