package com.example.androidexercises

import androidx.activity.ComponentActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun UserAppView() {
    val navControl = rememberNavController()

    Scaffold(
        bottomBar = { BottomNavBar(navControl) }
    ) {
        NavHost(navController = navControl, startDestination = "user_input") {
            composable("user_input") { UserInputView() }
            composable("user_list") { UserListView() }
        }
    }
}

@Composable
fun BottomNavBar(navC: NavHostController) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(colorResource(R.color.my_main_color)),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Icon(
            painter = painterResource(R.drawable.ic_input),
            contentDescription = "info",
            Modifier.clickable { navC.navigate("user_input") },
            tint = Color.White
        )
        Icon(
            painter = painterResource(R.drawable.ic_users),
            contentDescription = "info",
            Modifier.clickable { navC.navigate("user_list") },
            tint = Color.White
        )
    }
}

@Composable
fun UserInputView() {
    var nameState = remember { mutableStateOf("") }
    var ageState = remember { mutableStateOf("") }

    val personVM: PersonViewModel = viewModel(LocalContext.current as ComponentActivity)

    Column(
        modifier = Modifier.padding(10.dp)
    ) {
        UserInputField(nameState, "Name")
        UserInputField(ageState, "Age")

        OutlinedButton(
            modifier = Modifier
                .padding(vertical = 5.dp)
                .border(1.dp, Color.Black),
            onClick = { handleButtonClick(nameState, ageState, personVM)}
        ) {
            Text(text = "Add person", fontWeight = FontWeight.Bold, fontSize = 16.sp)
        }
    }
}

//This is just a helper function, not a composable
fun handleButtonClick(
    nameState: MutableState<String>,
    ageState: MutableState<String>,
    personVM: PersonViewModel
){
    //Creating new person and adding it to the view model list
    personVM.persons.add(
        Person(
            nameState.value.toString(),
            ageState.value.toInt()
        )
    )
    nameState.value = ""
    ageState.value = ""
}

@Composable
fun UserInputField(state: MutableState<String>, label: String) {
    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        value = state.value,
        onValueChange = { t ->
            state.value = t
        },
        label = { Text(text = label) }
    )
}

@Composable
fun UserListView() {
    val personVM: PersonViewModel = viewModel(LocalContext.current as ComponentActivity)

    LazyColumn() {
        items(personVM.persons) { person ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                elevation = 10.dp
            ) {
                Column(modifier = Modifier.padding(10.dp)) {
                    Text(text = "Name: ${person.name.toString()}", fontSize = 20.sp)
                    Text(text = "Age: ${person.age.toString()}", fontSize = 20.sp)
                }
            }
        }
    }
}