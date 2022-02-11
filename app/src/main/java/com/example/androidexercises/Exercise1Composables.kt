package com.example.androidexercises

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Task1_1(){
    Column(
        modifier = Modifier
            .background(Color.Black)
            .width(200.dp)
            .height(100.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.End
    ) {
        Text(text = "First text", color = Color.White)
        Text(text = "Second text", color = Color.White)
    }
}

@Composable
fun Task1_2(){
    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier.fillMaxWidth()
    ) {
        repeat(4){
            Box(
                modifier = Modifier
                    .background(Color.Black)
                    .width(50.dp)
                    .height(50.dp)
            )
        }
    }
}

@Composable
fun Task1_3(){
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        repeat(10) {
            Row() {
                repeat(5){
                    Text(text = "X", fontSize = 30.sp)
                }
            }
        }
    }
}

@Composable
fun Task1_4() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(250.dp)
            .padding(10.dp)
            .border(1.dp, Color.Black)
            .padding(horizontal = 10.dp),
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        FormRow(label = "First name:")
        FormRow(label = "Last name:")
        FormRow(label = "Age name:")
    }
}

@Composable
fun FormRow(label: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ){
        Text(text = label, fontSize = 20.sp, fontWeight = FontWeight.Bold)
        OutlinedTextField(value = "", onValueChange = {}, modifier = Modifier.width(240.dp))
    }
}