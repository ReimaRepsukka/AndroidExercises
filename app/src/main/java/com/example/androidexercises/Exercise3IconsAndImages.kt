package com.example.androidexercises

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun Task3_1() {

    var showImage by remember { mutableStateOf(false) }

    Column() {
        Icon(
            painter = painterResource(R.drawable.ic_facebook),
            contentDescription = "facebook",
            modifier = Modifier.clickable { showImage = !showImage }
        )

        if(showImage){
            Image(
                painter = painterResource(R.drawable.colorful),
                contentDescription = "colorful",
                modifier = Modifier
                    .size(300.dp)
                    .border(3.dp, Color.Black),
                contentScale = ContentScale.Crop
            )
        }
    }
}

@Composable
fun Task3_2() {
    //Array of image resource ids
    val images = arrayOf(
        R.drawable.colorful,
        R.drawable.colorful,
        R.drawable.wood,
        R.drawable.colorful,
        R.drawable.wood2,
        R.drawable.colorful,
        R.drawable.colorful,
        R.drawable.wood2
    )

    LazyColumn(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        //Creating image and spacer from each resource id in the array
        items(images){ resId ->
            Img(resId)
            Spacer(modifier = Modifier.height(10.dp))
        }
    }
}

@Composable
fun Img(resId: Int) {
    Image(
        painter = painterResource(resId), contentDescription = "",
        modifier = Modifier
            .size(200.dp, 150.dp)
            .border(3.dp, Color.Black),
        contentScale = ContentScale.Crop
    )
}

/**
 * Next composables for the navigation task
 */

@Composable
fun Task3_3() {
    val navControl = rememberNavController()

    Column {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(0.dp, 5.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            MenuIcon(navControl, R.drawable.ic_baseline_home_24 , "home" )
            MenuIcon(navControl, R.drawable.ic_facebook , "facebook" )
            MenuIcon(navControl, R.drawable.ic_chat , "chat" )
        }

        Divider( color = Color.Black, thickness = 3.dp )

        NavHost(navController = navControl, startDestination = "home") {
            composable(route = "home") {
                ImageView(R.drawable.superman)
            }
            composable(route = "facebook") {
                ImageView(R.drawable.wood)
            }
            composable(route = "chat") {
                ImageView(R.drawable.wood2)
            }
        }

    }
}

@Composable
fun MenuIcon(navControl: NavHostController, resId: Int, route: String) {
    Icon(
        painter = painterResource(resId),
        contentDescription = "",
        modifier = Modifier
            .size(50.dp)
            .clickable { navControl.navigate(route) }
    )
}

@Composable
fun ImageView(resId: Int) {

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        Image(painter = painterResource(resId), contentDescription = "")
    }

}


