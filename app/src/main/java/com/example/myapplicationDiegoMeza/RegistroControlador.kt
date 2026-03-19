package com.example.myapplicationDiegoMeza

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Preview(showBackground = true , showSystemUi = true)
@Composable
fun RegistroScreen(){

    Column() {


        Text("")
        TextField(value = "" , onValueChange = {})
        TextField(value = "" , onValueChange = {})
        Text("")


    }

}