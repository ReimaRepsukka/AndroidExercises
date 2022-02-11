package com.example.androidexercises

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
fun Task2_1() {

    //Exercises 1-3 here
    var text by remember { mutableStateOf("") }
    var clicks by remember { mutableStateOf(0) }

    Column() {
        TextField(value = text , onValueChange = { text = it })
        Button(onClick = { clicks++ }) { Text(text = "OK") }

        repeat(clicks){
            Text(text = text)
        }
    }
}

@Composable
fun Task2_4() {
    var isRegistered by remember { mutableStateOf(false)}

    Column() {
        Row() {
            Text(text = "Already registered")
            Checkbox(checked = isRegistered, onCheckedChange = {isRegistered = !isRegistered})
        }

        Text(
            text = if(isRegistered) "Log in" else "Register",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )

        if( !isRegistered ){
            UserInputField(label = "First name")
            UserInputField(label = "Last name")
        }

        UserInputField(label = "Username")
        UserInputField(label = "Password")
    }
}

@Composable
fun UserInputField(label: String) {
    OutlinedTextField(
        value = "",
        onValueChange = {},
        label = { Text(text = label) }
    )
}