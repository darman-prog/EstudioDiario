package com.example.myapplicationDiegoMeza

import android.R
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Preview(showBackground = true, showSystemUi = true
)
@Composable
fun EncabezadoControlador(Usuario:(String) = ""){

    Row() {

        Text("Bienvenido $Usuario")

    }

}