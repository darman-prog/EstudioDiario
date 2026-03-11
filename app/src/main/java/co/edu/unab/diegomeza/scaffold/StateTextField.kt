package co.edu.unab.diegomeza.scaffold

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier

@Composable
fun StateTextField(){
    var inputText by remember { mutableStateOf("Ingresa un texto") }

    Scaffold (){ innerPadding ->

        Column (modifier = Modifier.padding(innerPadding)){

            TextField(
                value = inputText,
                onValueChange = {inputText = it}
            )

            Text(text = "Resultado : $inputText")

        }

    }


}